import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUpPageView from '../views/SignUpPageView.vue'
import LogInPage from '@/components/LogInPage.vue'

Vue.use(VueRouter)

  const routes = [{
    path : "/sign-up-page",
    name : "SignUpPage",
    component : SignUpPageView
  },
  {
    path : "/log-in-page",
    name : "LogInPage",
    component : LogInPage,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
