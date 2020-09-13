<template>
  <div id="favoriteList">
    <div class="wrapper">
      <div id="favorite" v-for="post in postList" v-bind:key="post.id">
        <!--<a v-bind:href="post.link" target="_blank">-->
        <img v-bind:src="post.img" />
        <p>posted by: {{post.userEmail}}</p>
        <p>사진번호: {{post.pictureNumber}}</p>
        <!--태그 가지고 오는 걸로 바꾸기.-->
        <p>포스트 아이디 : {{post.postId}}</p>
        <p>포스트점아이디:{{post.id}}</p>
        <!--삭제버튼-->
        <button
          type="button"
          v-on:click.stop="deleteFavorite(post.pictureNumber,post.postId)"
        >&#x00D7;</button>
        <!--</a>-->
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
      nextPostId: 0,
    };
  },
  created: function () {
    console.log("favoritelist-created");
    EventBus.$on("favorite-req", this.favoriteReq);
  },
  methods: {
    favoriteReq: function () {
      let self = this;
      this.$axios
        .get("http://127.0.0.1:80/favorite/" + storage.getItem("userNumber")) //favoritelist출력
        .then((res) => {
          for (var i = 0; i < res.data.pictureNumberList.length; i++) {
            self.postList.push({
              postId: this.nextPostId++,
              pictureNumber: res.data.pictureNumberList[i],
              link: "",
              userEmail: res.data.pictureObject[i].userId.userEmail,
              img: res.data.img[i],
            });
          }
          console.log(res.data);
        });
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
      });
    },
  },
};
</script>
<style scoped>
/* body {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  margin-top: 16px;
  margin-bottom: 16px;
} */
div#favoriteList {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.wrapper {
  display: flex;
  max-width: 500px;
  flex-wrap: wrap;
  padding-top: 12px;
}
#favorite {
  box-shadow: rgba(0, 0, 0, 0.117647) 0px 1px 6px,
    rgba(0, 0, 0, 0.117647) 0px 1px 4px;
  max-width: 140px;
  margin: 12px;
  transition: 0.15s all ease-in-out;
}
#favorite:hover {
  transform: scale(1.1);
}
img {
  height: 100px;
  width: 130px;
}
p {
  font-size: 10px;
  padding: 4px;
}
</style>