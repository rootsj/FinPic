
<template>
  <div id="app">
    <div id="nav">
      <MainPageButton />
      <div class="TagSearchBoxNoAll">
        <TagSearchBoxNoAll />
      </div>
      <ShowProfileImg />
      <ShowUserEmail />
      <ShowUserInfo />

      <button
        class="updateButton"
        type="button"
        v-on:click="update()"
        v-if="myUserNumber == userNumber"
      >Update Info</button>
      <div class="UpdateProfileImg">
        <UpdateProfileImg v-if="toggleUpdate" />
      </div>
      <div class="DelProfileImgBtn">
        <DelProfileImgBtn v-if="toggleUpdate" />
      </div>
      <div class="UpdateInfo">
        <UpdateInfo v-if="toggleUpdate" />
      </div>
      <div class="UpdateSNSinfo">
        <UpdateSNSinfo v-if="toggleUpdate" />
      </div>
      <div class="DeleteID">
        <DeleteID v-if="toggleUpdate" />
      </div>
      <ShowPost v-if="toggleMyPicture" />
      <MyPageUserImage v-if="toggleMyPicture" />
      <FollowButton />
      <FavoriteListButton />
    </div>
    <router-view />
  </div>
</template>
<script>
const storage = window.sessionStorage;

import MainPageButton from "../../components/MainPageButton.vue";
import TagSearchBoxNoAll from "../../components/TagSearchBoxNoAll.vue";
import ShowUserInfo from "../../components/ShowUserInfo.vue";
import ShowUserEmail from "../../components/ShowUserEmail.vue";
import UpdateProfileImg from "../../components/UpdateProfileImg.vue";
import UpdateSNSinfo from "../../components/UpdateSNSinfo.vue";
import ShowProfileImg from "../../components/ShowProfileImg.vue";
import DelProfileImgBtn from "../../components/DelProfileImgBtn.vue";
import UpdateInfo from "../../components/UpdateInfo.vue";
import ShowPost from "../../components/ShowPost.vue";
import DeleteID from "../../components/DeleteID.vue";
import FollowButton from "../../components/FollowButton.vue";
import MyPageUserImage from "../../components/MyPageUserImage.vue";
import FavoriteListButton from "../../components/FavoriteListButton.vue";

export default {
  name: "App",
  data: function () {
    return {
      pictureNumber: "",
      userEamil: "",
      userNumber: "",
      myUserNumber: "",
      toggleUpdate: false,
      toggleTagPicture: false,
      toggleMyPicture: true,
      toggleFavorite: false,
      toggleFollowList: false,
      toggleOnePicture: false,
    };
  },
  methods: {
    update: function () {
      this.toggleUpdate = true;
      this.toggleTagPicture = false;
      this.toggleMyPicture = false;
      this.toggleFavorite = false;
      this.toggleFollowList = false;
      this.toggleOnePicture = false;
    },
  },
  components: {
    MainPageButton,
    TagSearchBoxNoAll,
    ShowUserInfo,
    ShowUserEmail,
    UpdateProfileImg,
    UpdateSNSinfo,
    ShowProfileImg,
    DelProfileImgBtn,
    UpdateInfo,
    ShowPost,
    DeleteID,
    FollowButton,
    MyPageUserImage,
    FavoriteListButton,
  },
  created() {
    this.pictureNumber = storage.getItem("PictureNumber");
    this.userEmail = storage.getItem("otherUserEmail");
    this.userNumber = storage.getItem("otherUserNumber");
    this.myUserNumber = storage.getItem("userNumber");
    console.log(this.userEmail);
  },
};
</script>
<style>
#app {
  text-align: center;
  padding: 30px;
}

div.UpdateProfileImg {
  position: relative;
}
div.DelProfileImgBtn {
  position: relative;
  top: 30px;
}
div.UpdateInfo {
  position: relative;
  top: 30px;
}
div.UpdateSNSinfo {
  position: relative;
  top: 40px;
}
div.DeleteID {
  position: relative;
  top: 60px;
}

.updateButton {
  width: 100px;
  height: 40px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: 5px;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: white;
  background-color: #323232;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
}
.updateButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare_0";
  src: url("./../../assets/NanumSquare.ttf");
}
</style>