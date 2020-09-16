<template>
  <div id="searchBoxInMypage" class="mypageimgContainer">
    <div class="wrapper">
      <div class="mypageimg" id="show" v-for="post in postList" v-bind:key="post.postId">
        <img
          class="pageImg"
          v-bind:src="post.img"
          v-on:click="mypage(post.pictureNumber,post.userEmail,post.userNumber)"
        />
        <div class="userBtn">
          <LikeButton :pictureNumber="post.pictureNumber" />
          <ReportButton :pictureNumber="post.pictureNumber" />
          <FavoriteButton :pictureNumber="post.pictureNumber" />
          <DeleteMyImg style="position:relative;left:5px;" :pictureNumber="post.pictureNumber" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;
import EventBus from "../EventBus/EventBus.js";
import LikeButton from "./LikeButton.vue";
import ReportButton from "./ReportButton.vue";
import FavoriteButton from "./FavoriteButton.vue";
import DeleteMyImg from "./DeleteMyImg.vue";

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
    FavoriteButton,
    DeleteMyImg,
  },
  mounted() {
    EventBus.$on("search", (x) => {
      console.log("-----------");
      this.postList = [];
      x.data.pictureObject.sort(function (a, b) {
        return a.pictureNumber - b.pictureNumber;
      });
      console.log(x);
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
      .get("http://localhost:80/pictures/" + self.otherUserNumber)
      .then((res) => {
        this.postList = [];
        res.data.pictureObject.sort(function (a, b) {
          return a.pictureNumber - b.pictureNumber;
        });
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
  },
  methods: {
    mypage: function (x, y, z) {
      EventBus.$off("search");
      storage.setItem("otherUserEmail", y);
      storage.setItem("otherUserNumber", z);
      storage.setItem("pictureNumber", x);
      this.$router.push("/mypage");
      this.$router.go("/");
    },
  },
};
</script>
<style scoped>
.mypageimgContainer {
  position: absolute;
  width: 120%;
  left: 0%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.wrapper {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
}
.mypageimg {
  max-width: 25%;
  margin: 2%;
  margin-bottom: 5%;
  transition: 0.15s all ease-in-out;
}

.userBtn {
  position: relative;
  left: 20%;
  width: 30%;
  display: flex;
}
.pageImg {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.pageImg:hover {
  transform: scale(1.05);
}
</style>