import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$axios = axios;
const storage = window.sessionStorage;
axios.defaults.headers.common["jwt-auth-token"] = storage.getItem("jwt-auth-token");

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
