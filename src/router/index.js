import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUpPageView from '../views/SignUpPageView.vue'

Vue.use(VueRouter)

  const routes = [{
    path : "/signUpPage",
    name : "SignUpPage",
    component : SignUpPageView

  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
