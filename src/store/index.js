import Vue from 'vue'
import Vuex from 'vuex'
import auth from './auth'
import category from './category'
import record from './record'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    error: null,
    message: null
  },
  mutations: {
    setError (state, error) {
      state.error = error
    },
    clearError (state) {
      state.error = null
    },
    setMessage (state, message) {
      state.message = message
    },
    clearMessage (state) {
      state.message = null
    }
  },
  actions: {
  },
  getters: {
    error: state => state.error,
    message: state => state.message
  },
  modules: {
    auth, category, record
  }
})
