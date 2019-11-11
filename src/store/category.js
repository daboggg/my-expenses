import Vue from 'vue'

const port = process.env.VUE_APP_SERVERPORT
export default {
  state: {
    categories: []
  },
  mutations: {
    categories (state, categories) {
      state.categories = categories
    },
    addCategory (state, category) {
      state.categories.push(category)
    }
  },
  actions: {
    async createCategory ({ commit, getters }, { name }) {
      try {
        const res = await Vue.http.post(`http://localhost:${port}/api/category`,
          JSON.stringify({ name: name }), {
            headers: {
              'Content-Type': 'application/json;charset=cp1251',
              'Token': getters.getToken
            }
          })
        const data = await res.json()
        commit('setMessage', 'category created')
        commit('addCategory', data)
        console.log(data)
        return data
      } catch (e) {
        console.log(e)
        commit('setError', e.body.message)
        console.log(e.body.message)
        throw e
      }
    },
    async getCategories ({ commit, getters }) {
      try {
        const res = await Vue.http.get(`http://localhost:${port}/api/category`, {
          headers: {
            'Content-Type': 'application/json',
            'Token': getters.getToken
          }
        })
        const data = await res.json()
        commit('categories', data)
        return data
      } catch (e) {
        console.log(e)
        throw e
      }
    },
    async categoryUpdate ({ commit, getters, dispatch }, { id, name }) {
      console.log(id)
      console.log(name)
      try {
        const res = await Vue.http.put(`http://localhost:${port}/api/category`,
          JSON.stringify({ id: id, name: name }), {
            headers: {
              'Content-Type': 'application/json',
              'Token': getters.getToken
            }
          })
        dispatch('getCategories')
        const data = await res.json()
        commit('setMessage', 'category updated')
        return data
      } catch (e) {
        commit('setError', e.body.message)
        console.log(e.body.message)
        throw e
      }
    }
  },
  getters: {
    categories: state => state.categories
  }
}
