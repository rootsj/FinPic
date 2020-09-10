
<template>
  <div id="app">
    <div id="nav">
        <MainPageButton/><br>
        <MyPageButton/>  
        <LogInButton/>
        <UploadButton/>
        <LogOutButton/>
        <!--<TagSearchBox/>-->
        <TagSearchBoxNoAll/>
        <AllImage/>
    </div>
    <router-view/>
  </div>
</template>
<script>
import LogInButton from '../../components/LogInButton.vue'
import LogOutButton from '../../components/LogOutButton.vue'
import UploadButton from '../../components/UploadButton.vue'
//import TagSearchBox from '../../components/TagSearchBox.vue'
import MainPageButton from '../../components/MainPageButton.vue'
import MyPageButton from '../../components/MyPageButton.vue'
import TagSearchBoxNoAll from '../../components/TagSearchBoxNoAll.vue'
import AllImage from '../../components/AllImage.vue'

export default {
    name : "App",
    components : {
      LogInButton,
      LogOutButton,
      UploadButton,
      //TagSearchBox,
      MainPageButton,
      MyPageButton,
      TagSearchBoxNoAll,
      AllImage,
    },
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
