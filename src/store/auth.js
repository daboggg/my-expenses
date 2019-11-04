import Vue from 'vue'
const port = process.env.VUE_APP_SERVERPORT
export default {
  state: {
    name: null,
    token: null
  },
  mutations: {
    login (state, login) {
      state.name = login.name
      state.token = login.token
      sessionStorage.setItem('name', login.name)
      sessionStorage.setItem('token', login.token)
    },
    logout (state) {
      state.name = null
      state.token = null
      sessionStorage.removeItem('name')
      sessionStorage.removeItem('token')
    }
  },
  actions: {
    async login ({ commit }, { email, password }) {
      try {
        const res = await Vue.http.post(`http://localhost:${port}/api/login`,
          JSON.stringify({ email: email, password: password }),
          { 'Content-Type': 'application/json' })

        const login = await res.json()
        commit('login', { name: login.firstName, token: login.token })
      } catch (e) {
        commit('setError', e.body.message)
        console.log(e.body.message)
        throw e
      }
    },
    async register ({ commit }, { email, password, name }) {
      try {
        const res = await Vue.http.put(`http://localhost:${port}/api/register`,
          JSON.stringify({ email: email, password: password, name: name }),
          { 'Content-Type': 'application/json' })
        const login = await res.json()
        commit('login', { name: login.firstName, token: login.token })
        console.log(res)
      } catch (e) {
        console.log(e)
        commit('setError', e.body.message)
        throw e
      }
    }
  },
  getters: {
    getName: state => state.name,
    getToken: state => state.token
  }
}
