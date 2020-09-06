
<template>
  <div id="app">
    <div id="nav">
        <router-link to = "/sign-up-page">signUpPage</router-link><br>
        <SignUpButton/><br>
        <LogInButton/><br>
        <LogOutButton/><br>
    </div>
    <router-view/>
  </div>
</template>
<script>
import SignUpButton from '@/components/SignUpButton.vue'
import LogInButton from '@/components/LogInButton.vue'
import LogOutButton from '@/components/LogOutButton.vue'

export default {
    name : "App",
    components : {
      SignUpButton,
      LogInButton,
      LogOutButton,
    }
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
