import Vue from 'vue'

const port = process.env.VUE_APP_SERVERPORT
export default {
  actions: {
    async recordCreate ({ commit, getters }, record) {
      try {
        await Vue.http.post(`http://localhost:${port}/api/record`,
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
        const res = await Vue.http.get(`http://localhost:${port}/api/record`, {
          headers: {
            'Content-Type': 'application/json',
            'Token': getters.getToken
          }
        })
        const data = await res.json()
        return data
      } catch (e) {
        console.log(e)
        throw e
      }
    }
  }
}
