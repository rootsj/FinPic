# FinPic

# we are going to make funny webpage!

# pom.xml


# application.properties


# Vue 설정
  - vue create frontend
    1) Manually select features
    2) Router 추가 설정 나머지 default
    
  - main.js (내용)
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$axios = axios;

const storage = window.sessionStorage;
axios.defaults.headers.common["jwt-auth-token"] = storage.getItem("jwt-auth-token");

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

  - Vuex 추가 설정 
    
