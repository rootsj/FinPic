
<template>
  <div id="app">
    <div id="nav">
      <div class="LogInButton">
        <LogInButton />
      </div>
      <div class="MyPageButton">
        <MyPageButton />
      </div>

      <div class="UploadButton">
        <UploadButton />
      </div>
      <br />
      <div class="LogOutButton">
        <LogOutButton />
      </div>

      <br />
      <MainPageButton />
      <br />

      <!--<TagSearchBox/>-->
      <div class="TagSearchBoxNoAll">
        <TagSearchBoxNoAll />
      </div>
      <div class="AllImage">
        <AllImage />
      </div>
    </div>
    <router-view />
  </div>
</template>
<script>
import LogInButton from "../../components/LogInButton.vue";
import LogOutButton from "../../components/LogOutButton.vue";
import UploadButton from "../../components/UploadButton.vue";
//import TagSearchBox from '../../components/TagSearchBox.vue'
import MainPageButton from "../../components/MainPageButton.vue";
import MyPageButton from "../../components/MyPageButton.vue";
import TagSearchBoxNoAll from "../../components/TagSearchBoxNoAll.vue";
import AllImage from "../../components/AllImage.vue";

export default {
  name: "App",
  components: {
    LogInButton,
    LogOutButton,
    UploadButton,
    //TagSearchBox,
    MainPageButton,
    MyPageButton,
    TagSearchBoxNoAll,
    AllImage,
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
#header nav {
  position: absolute;
  top: 0;
  height: inherit;
  line-height: inherit;
}

div.LogInButton {
  position: absolute;
  top: 20px;
  right: 30px;
}
div.LogOutButton {
  position: absolute;
  top: 20px;
  right: 30px;
}
div.MyPageButton {
  position: absolute;
  top: 20px;
  right: 250px;
}
div.UploadButton {
  position: absolute;
  top: 20px;
  right: 140px;
}

.AllImage {
  position: relative;
  top: 20px;
}
</style>
