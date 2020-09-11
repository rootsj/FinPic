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