# FinPic

# we are going to make funny webpage!

# pom.xml


# application.properties


# Vue 설정
  - vue create frontend<br>
    1) Manually select features<br>
    2) Router 추가 설정 나머지 default<br>
    
  - main.js (내용)<br>
import Vue from 'vue'<br>
import App from './App.vue'<br>
import router from './router'<br>
import axios from 'axios'<br><br>

Vue.config.productionTip = false<br>
Vue.prototype.$axios = axios;<br><br>

const storage = window.sessionStorage;<br>
axios.defaults.headers.common["jwt-auth-token"] = storage.getItem("jwt-auth-token");<br>

new Vue({<br>
  router,<br>
  render: h => h(App)<br>
}).$mount('#app')<br><br>

  - Vuex 추가 설정 
    
