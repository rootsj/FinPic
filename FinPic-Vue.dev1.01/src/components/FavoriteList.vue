<template>
  <div id="favoriteList">
    <div class="wrapper">
      <div id="favorite" v-for="post in postList" v-bind:key="post.id">
        <button class="deletebtn" v-on:click.stop="deleteFavorite(post.pictureNumber,post.postId)">
          <i class="fa fa-close"></i>
        </button>
        <img
          class="favoriteListImgStyle"
          v-bind:src="post.img"
          v-on:click="mypage(post.pictureNumber,post.userEmail,post.userNumber)"
        />
        <div id="postinfo">
          <p>posted by: {{post.userEmail}}</p>
          <p>사진번호: {{post.pictureNumber}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EventBus from "../EventBus/EventBus.js";
const storage = window.sessionStorage;

export default {
  name: "FavoriteList",
  data: function () {
    return {
      postList: [],
      dummy: 0,
    };
  },
  created: function () {
    this.dummy = 1;
    console.log("favoritelist-created");
    EventBus.$on("favorite-req", this.favoriteReq);
  },
  watch: {
    dummy: function () {
      console.log("------FAVORITE REQ -------");
      let self = this;
      this.postList = [];
      this.$axios
        .get("http://127.0.0.1:80/favorite/" + storage.getItem("userNumber")) //favoritelist출력
        .then((res) => {
          res.data.pictureObject.sort(function (a, b) {
            return a.pictureNumber - b.pictureNumber;
          });
          for (var i = 0; i < res.data.pictureNumberList.length; i++) {
            self.postList.push({
              postId: i,
              pictureNumber: res.data.pictureNumberList[i],
              link: "",
              userNumber: res.data.pictureObject[i].userId.userNumber,
              userEmail: res.data.pictureObject[i].userId.userEmail,
              img: res.data.img[i],
            });
          }
          console.log(res.data);
        });
    },
  },
  methods: {
    favoriteReq: function () {
      console.log("------FAVORITE REQ -------");
      let self = this;
      this.postList = [];
      this.$axios
        .get("http://127.0.0.1:80/favorite/" + storage.getItem("userNumber")) //favoritelist출력
        .then((res) => {
          res.data.pictureObject.sort(function (a, b) {
            return a.pictureNumber - b.pictureNumber;
          });
          for (var i = 0; i < res.data.pictureNumberList.length; i++) {
            self.postList.push({
              postId: i,
              pictureNumber: res.data.pictureNumberList[i],
              link: "",
              userNumber: res.data.pictureObject[i].userId.userNumber,
              userEmail: res.data.pictureObject[i].userId.userEmail,
              img: res.data.img[i],
            });
          }
          console.log(res.data);
        });
    },
    mypage: function (x, y, z) {
      EventBus.$off("search");
      storage.setItem("otherUserEmail", y);
      storage.setItem("otherUserNumber", z);
      storage.setItem("pictureNumber", x);
      this.$router.push("/mypage");
      this.$router.go("/");
    },
    deleteFavorite: function (pictureNumber, postId) {
      let self = this;
      this.$axios({
        method: "delete",
        url:
          "http://127.0.0.1:80/favorite/" +
          storage.getItem("userNumber") +
          "/" +
          pictureNumber,
      }).then((res) => {
        console.log("favorite삭제 결과:" + res.data);
        let index = self.postList.findIndex(function (item) {
          return item.postId === postId; //삭제버튼 누른 인덱스 같으면
        });
        self.postList.splice(index, 1); //postList 삭제
        EventBus.$off("favorite-req");
        // EventBus.$emit('favoriteChange',res.data);
      });
    },
  },
};
</script>
<style scoped>
#favoriteList {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.wrapper {
  display: flex;
  max-width: 500px;
  flex-wrap: wrap;
  padding-top: 5px;
}
#favorite {
  box-shadow: rgba(0, 0, 0, 0.117647) 0px 1px 6px,
    rgba(0, 0, 0, 0.117647) 0px 1px 4px;
  max-width: 140px;
  max-height: 250px;
  margin: 12px;
  transition: 0.15s all ease-in-out;
  background-color: white;
}
#favorite:hover {
  transform: scale(1.1);
}
img {
  height: 100px;
  width: 130px;
}
#postinfo {
  font-family: "NanumSquare_0", sans-serif;
  font-size: 10px;
  padding: 4px;
}
.deletebtn {
  float: right;
  background-color: rgb(145, 144, 144);
  border: none;
  color: white;
  padding: 4px 4px;
  font-size: 10px;
  cursor: pointer;
}
</style>