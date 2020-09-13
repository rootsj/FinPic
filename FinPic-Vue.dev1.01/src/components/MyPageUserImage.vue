<template>
  <div id="searchBoxInMypage">
    <div id="show" v-for="post in postList" v-bind:key="post.postid">
      <img v-bind:src="post.img" />
      <LikeButton :pictureNumber="post.pictureNumber" />
      <ReportButton :pictureNumber="post.pictureNumber" />
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;
import EventBus from "../EventBus/EventBus.js";
import LikeButton from "./LikeButton.vue";
import ReportButton from "./ReportButton.vue";

export default {
  name: "MyPageUserImage",
  data: function () {
    return {
      otherUserNumber: storage.getItem("otherUserNumber"),
      postList: [],
    };
  },
  components: {
    LikeButton,
    ReportButton,
  },
  mounted() {
    EventBus.$on("search", (x) => {
      this.postList = [];
      for (let i = 0; i < x.data.pictureNumberList.length; i++) {
        this.postList.push({
          postId: i,
          pictureNumber: x.data.pictureNumberList[i],
          userEmail: x.data.pictureObject[i].userId.userEmail,
          userNumber: x.data.pictureObject[i].userId.userNumber,
          img: x.data.img[i],
        });
      }
    });
  },
  created() {
    let self = this;
    this.$axios
      .get("http://127.0.0.1:80/pictures/" + self.otherUserNumber)
      .then((res) => {
        for (let i = 0; i < res.data.pictureNumberList.length; i++) {
          self.postList.push({
            postId: i,
            pictureNumber: res.data.pictureNumberList[i],
            userEmail: res.data.pictureObject[i].userId.userEmail,
            userNumber: res.data.pictureObject[i].userId.userNumber,
            img: res.data.img[i],
          });
        }
      });
    console.log("MyPageUserImage");
  },
};
</script>
<style>
.UpdateDelProfileImgButton {
  width: 40%;
  height: 30px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: 15px;
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
  position: relative;
  top: 10px;
}
.DelProfileImgButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare_0";
  src: url("./../assets/NanumSquare.ttf");
}
</style>