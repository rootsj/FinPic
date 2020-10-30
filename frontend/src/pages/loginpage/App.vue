
<template>
  <div id="app">
    <div id="nav">
      <MainPageButton />
      <br />
      <LogInPage />
      <br />
      <SignUpButton />
      <br />
    </div>
    <router-view />
  </div>
</template>
<script>
import LogInPage from "../../components/LogInPage.vue";
import SignUpButton from "../../components/SignUpButton.vue";
import MainPageButton from "../../components/MainPageButton.vue";

export default {
  name: "App",
  components: {
    SignUpButton,
    LogInPage,
    MainPageButton,
  },
};
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
