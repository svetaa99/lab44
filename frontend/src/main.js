import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { BootstrapVue } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import vuetify from '@/plugins/vuetify'
import 'vue-search-select/dist/VueSearchSelect.css'
import DateFilter from './filters/date'

Vue.config.productionTip = false
Vue.filter('date', DateFilter ) // register filter globally
Vue.use(BootstrapVue)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
