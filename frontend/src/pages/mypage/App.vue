
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
      <MainPageButton />
      <div class="TagSearchBoxNoAll">
        <TagSearchBoxNoAll />
      </div>
      <div id="info" class="info">
        <ShowProfileImg class="info-item" />
        <ShowUserEmail class="info-item" />
        <ShowUserInfo class="info-item" />
        <button
          class="updateButton"
          type="button"
          v-on:click="mypost()"
          v-if="myUserNumber == userNumber"
        >My Post</button>
        <FollowButton />
        <br />
        <FavoriteListButton :toggleFollowList="toggleFollowList" />
        <br />
        <button
          class="updateButton"
          type="button"
          v-on:click="update()"
          v-if="myUserNumber == userNumber"
        >Update Info</button>
      </div>
      <div></div>
      <div id="content" class="content">
        <FollowListButton :toggleFollowList="toggleFollowList" />
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
        <FavoriteList v-if="toggleFavorite" />
      </div>
    </div>
    <router-view />
  </div>
</template>
<script>
const storage = window.sessionStorage;
import LogInButton from "../../components/LogInButton.vue";
import LogOutButton from "../../components/LogOutButton.vue";
import UploadButton from "../../components/UploadButton.vue";
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
import MyPageUserImage from "../../components/MyPageUserImage.vue";
import FavoriteListButton from "../../components/FavoriteListButton.vue";
import FavoriteList from "../../components/FavoriteList.vue";
import EventBus from "../../EventBus/EventBus.js";
import FollowListButton from "../../components/FollowListButton.vue";
import FollowButton from "../../components/FollowButton.vue";

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
    mypost: function () {
      this.toggleUpdate = false;
      this.toggleTagPicture = false;
      this.toggleMyPicture = true;
      this.toggleFavorite = false;
      this.toggleFollowList = false;
      this.toggleOnePicture = false;
    },
    favorite: function () {
      this.toggleUpdate = false;
      this.toggleTagPicture = false;
      this.toggleMyPicture = false;
      this.toggleFavorite = true;
      this.toggleFollowList = false;
      this.toggleOnePicture = false;
    },
  },
  components: {
    LogInButton,
    LogOutButton,
    UploadButton,
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
    MyPageUserImage,
    FavoriteListButton,
    FavoriteList,
    FollowListButton,
    FollowButton,
  },
  created() {
    this.pictureNumber = storage.getItem("PictureNumber");
    this.userEmail = storage.getItem("otherUserEmail");
    this.userNumber = storage.getItem("otherUserNumber");
    this.myUserNumber = storage.getItem("userNumber");
    EventBus.$on("favoriteToggle", (x) => {
      this.toggleUpdate = x;
      this.toggleTagPicture = x;
      this.toggleMyPicture = x;
      this.toggleFavorite = true;
      this.toggleFollowList = x;
      this.toggleOnePicture = x;
    });
    EventBus.$on("followToggle", (x) => {
      this.toggleUpdate = x;
      this.toggleTagPicture = x;
      this.toggleMyPicture = x;
      this.toggleFavorite = x;
      this.toggleFollowList = true;
      this.toggleOnePicture = x;
    });
    console.log(this.userEmail);
  },
};
</script>
<style>
#app {
  text-align: center;
  padding: 30px;
}
.info {
  position: absolute;
  width: 38%;
  top: 220px;
  left: 10%;
}
.content {
  align-content: center;
  position: absolute;
  top: 210px;
  width: 40%;
  left: 38%;
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
  width: 30%;
  height: 25px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: inherit;
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

.UploadButton {
  position: absolute;
  top: 20px;
  right: 250px;
}
</style>