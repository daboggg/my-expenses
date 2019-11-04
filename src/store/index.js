import Vue from 'vue'
import Vuex from 'vuex'
import auth from './auth'
import category from './category'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    error: null
  },
  mutations: {
    setError (state, error) {
      state.error = error
    },
    clearError (state) {
      state.error = null
    }
  },
  actions: {
  },
  getters: {
    error: state => state.error
  },
  modules: {
    auth, category
  }
})
