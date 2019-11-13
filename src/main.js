import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import dateFilter from './filters/date.filter'
import currencyFilter from './filters/currency.filter'
import VueResource from 'vue-resource'
import VueApexCharts from 'vue-apexcharts'
import Donut from 'vue-css-donut-chart'
import 'vue-css-donut-chart/dist/vcdonut.css'

Vue.use(VueResource)
Vue.use(Donut)
Vue.component('apexchart', VueApexCharts)
Vue.filter('date', dateFilter)
Vue.filter('currency', currencyFilter)
Vue.http.options.emulateJSON = true
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
