import Vue from 'vue'
const port = process.env.VUE_APP_SERVERPORT
export default {
  state: {
    name: '',
    token: ''
  },
  mutations: {
    login (state, login) {
      state.name = login.name
      state.token = login.token
    }
  },
  actions: {
    async login ({ dispatch, commit }, { email, password }) {
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
    }
  },
  getters: {
    getName: state => state.name,
    getToken: state => state.token
  }
}
