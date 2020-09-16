<template>
  <div id="favoriteList">
    <div class="wrapper">
      <div id="favorite" v-for="post in postList" v-bind:key="post.id">
        <button class="deletebtn" v-on:click.stop="deleteFavorite(post.pictureNumber,post.postId)">
          <i class="fa fa-close"></i>
        </button>
        <br />
        <img
          id="postImg"
          v-bind:src="post.img"
          v-on:click="mypage(post.pictureNumber,post.userEmail,post.userNumber)"
        />
        <br />
        <div id="postinfo">
          <p>
            <img src="./../assets/heart.png" style="height:15px;width: 15px; margin-right:5px;" />
            {{post.userName}}
          </p>
          <p>{{post.userEmail}}</p>
          <!-- <p>사진번호: {{post.pictureNumber}}</p> -->
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
        .get(
          "http://localhost:80/favorite/" + storage.getItem("userNumber")
        ) //favoritelist출력
        .then((res) => {
          //중복 태그 순서 변경
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
              userName: res.data.pictureObject[i].userId.userName,
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
        .get(
          "http://localhost:80/favorite/" + storage.getItem("userNumber")
        ) //favoritelist출력
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
              userName: res.data.pictureObject[i].userId.userName,
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
          "http://localhost:80/favorite/" +
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
      });
    },
  },
};
</script>
<style scoped>
#favoriteList {
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
  max-width: 100%;
  flex-wrap: wrap;
}
#favorite {
  height: 300px;
  width: 30%;
  margin: 12px;
  transition: 0.15s all ease-in-out;
  background-color: #323232;
  border-radius: 10px;
}
#favorite:hover {
  transform: scale(1.1);
  border: 1px solid #777;
}
#postImg {
  position: relative;
  width: 100%;
  height: 70%;
  object-fit: cover;
}
#postinfo {
  height: 10%;
  margin-left: 4px;
  float: left;
}
p {
  font-family: "NanumSquare_0", sans-serif;
  font-size: 12px;
  font-weight: bold;
  color: white;
  line-height: 4px;
}
.deletebtn {
  float: right;
  background-color: #323232;
  border: none;
  color: white;
  padding: 4px 4px;
  font-size: 10px;
  cursor: pointer;
  border-radius: 10px;
}
</style>