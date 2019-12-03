import Vue from 'vue'

const ipEndPort = process.env.VUE_APP_SERVERIPENDPORT
export default {
  state: {
    records: []
  },
  mutations: {
    records (state, records) {
      state.records = records
    }
  },
  actions: {
    async recordCreate ({ commit, getters }, record) {
      try {
        await Vue.http.post(`http://${ipEndPort}/api/record`,
          JSON.stringify({ ...record }), {
            headers: {
              'Content-Type': 'application/json',
              'Token': getters.getToken
            }
          })
        commit('setMessage', 'record created')
      } catch (e) {
        commit('setError', e.body.message)
        console.log(e.body.message)
        throw e
      }
    },
    async getRecords ({ commit, getters }) {
      try {
        const res = await Vue.http.get(`http://${ipEndPort}/api/record`, {
          headers: {
            'Content-Type': 'application/json',
            'Token': getters.getToken
          }
        })
        const data = await res.json()
        commit('records', data)
        return data
      } catch (e) {
        commit('setError', e.body.message)
        console.log(e)
        throw e
      }
    },
    async getRecord ({ commit, getters }, id) {
      try {
        const res = await Vue.http.get(`http://${ipEndPort}/api/rec?id=${id}`, {
          headers: {
            'Content-Type': 'application/json',
            'Token': getters.getToken
          }
        })
        const data = await res.json()
        return data
      } catch (e) {
        commit('setError', e.body.message)
        console.log(e)
        throw e
      }
    }
  },
  getters: {
    records: state => state.records
  }
}
