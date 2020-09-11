
<template>
  <div id="app">
    <div id="nav">
      <MainPageButton />
      <TagSearchBoxNoAll />
      <ShowProfileImg />
      <ShowUserEmail />
      <ShowUserInfo />
      <button type = "button" v-on:click = "update()" v-if = "myUserNumber == userNumber">SHOWUPDATEPAGE</button>
      <UpdateSNSinfo v-if= "toggleUpdate"/>
      <UpdateInfo v-if = "toggleUpdate"/>
      <UpdateProfileImg v-if = "toggleUpdate"/>
      <DelProfileImgBtn v-if = "toggleUpdate"/>
      <DeleteID v-if = "toggleUpdate"/>
      <MyPageUserImage v-if = "toggleMyPicture"/>
      <FollowButton/>
      <FollowListButton/>
    </div>
    <router-view/>
  </div>
</template>
<script>
const storage = window.sessionStorage;

import MainPageButton from '../../components/MainPageButton.vue'
import TagSearchBoxNoAll from '../../components/TagSearchBoxNoAll.vue'
import ShowUserInfo from "../../components/ShowUserInfo.vue";
import ShowUserEmail from "../../components/ShowUserEmail.vue";
import UpdateProfileImg from "../../components/UpdateProfileImg.vue";
import UpdateSNSinfo from "../../components/UpdateSNSinfo.vue";
import ShowProfileImg from "../../components/ShowProfileImg.vue";
import DelProfileImgBtn from "../../components/DelProfileImgBtn.vue";
import UpdateInfo from "../../components/UpdateInfo.vue";
import MyPageUserImage from "../../components/MyPageUserImage.vue";
import DeleteID from "../../components/DeleteID.vue";
import FollowButton from "../../components/FollowButton.vue";
import FollowListButton from "../../components/FollowListButton.vue";

export default {
    name : "App",
    data : function(){
      return {
          pictureNumber : "",
          userEamil : "",
          userNumber : "",
          myUserNumber : "",
          toggleUpdate : false,
          toggleTagPicture : false,
          toggleMyPicture : true,
          toggleFavorite : false,
          toggleFollowList : false,
          toggleOnePicture : false,
      }
    },
    methods : {
      update : function () {
        this.toggleUpdate = true;
        this.toggleTagPicture = false;
        this.toggleMyPicture = false;
        this.toggleFavorite = false;
        this.toggleFollowList = false;
        this.toggleOnePicture = false;
      },

    },
    components : {
      MainPageButton,
      TagSearchBoxNoAll,
      ShowUserInfo,
      ShowUserEmail,
      UpdateProfileImg,
      UpdateSNSinfo,
      ShowProfileImg,
      DelProfileImgBtn,
      UpdateInfo,
      MyPageUserImage,
      DeleteID,
      FollowButton,
      FollowListButton,
    },
    created() {
      this.pictureNumber = storage.getItem("PictureNumber");
      this.userEmail = storage.getItem("otherUserEmail");
      this.userNumber = storage.getItem("otherUserNumber");
      this.myUserNumber = storage.getItem("userNumber");
      console.log(this.userEmail);
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
