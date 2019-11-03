import Vue from 'vue'
import Vuetify, { VSnackbar, VBtn, VIcon } from 'vuetify/lib'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import VuetifyToast from 'vuetify-toast-snackbar'

Vue.use(Vuetify, {
  components: {
    VSnackbar, VBtn, VIcon
  }
})

Vue.use(VuetifyToast)

export default new Vuetify({
  icons: {
    iconfont: 'mdi'
  }
})
