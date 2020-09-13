
<template>
  <div id="app">
    <div id="nav">
<<<<<<< HEAD
        <MainPageButton/>
        <SignUpPageView/>
    </div>
    <router-view/>
  </div>
</template>
<script>
import SignUpPageView from '../../views/SignUpPageView.vue'
import MainPageButton from '../../components/MainPageButton.vue'

export default {
    name : "App",
    components : {
      SignUpPageView,
      MainPageButton,
    }
}
=======
      <MainPageButton />
      <br />
      <SignUpPageView />
    </div>
    <router-view />
  </div>
</template>
<script>
import SignUpPageView from "../../views/SignUpPageView.vue";
import MainPageButton from "../../components/MainPageButton.vue";

export default {
  name: "App",
  components: {
    SignUpPageView,
    MainPageButton,
  },
};
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
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
