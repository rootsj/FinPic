
<template>
  <div id="app">
    <div id="nav">
<<<<<<< HEAD
        <MainPageButton/>
        <UploadPage/>
    </div>
    <router-view/>
  </div>
</template>
<script>
import UploadPage from '../../components/UploadPage.vue'
import MainPageButton from '../../components/MainPageButton.vue'

export default {
    name : "App",
    components : {
      UploadPage,
      MainPageButton,
    }
}
=======
      <MainPageButton />
      <UploadPage />
    </div>
    <router-view />
  </div>
</template>
<script>
import UploadPage from "../../components/UploadPage.vue";
import MainPageButton from "../../components/MainPageButton.vue";

export default {
  name: "App",
  components: {
    UploadPage,
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
