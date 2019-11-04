import Vue from 'vue'
const port = process.env.VUE_APP_SERVERPORT
export default {
  actions: {
    async createCategory ({ commit, getters }, { name }) {
      console.log(name)
      try {
        const res = await Vue.http.put(`http://localhost:${port}/api/category`,
          JSON.stringify({ name: name }), {
            headers: {
              'Content-Type': 'application/json',
              'Token': getters.getToken
            }
          })

        // eslint-disable-next-line no-unused-vars
        const category = await res.json()
      } catch (e) {
        commit('setError', e.body.message)
        console.log(e.body.message)
        throw e
      }
    }
  }
}
