<template>
  <div id="image_show_box">
    <div class="container">
      <div class="gallery">
        <div
          class="gallery-item"
          tabindex="0"
          id="show"
          v-for="post in postList"
          v-bind:key="post.postid"
        >
          <img
            class="gallery-image"
            v-bind:src="post.img"
            v-on:click="mypage(post.pictureNumber,post.userEmail,post.userNumber)"
          />
          <div class="gallery-item-info">
            <LikeButton :pictureNumber="post.pictureNumber" />
            <ReportButton :pictureNumber="post.pictureNumber" />
            <FavoriteButton :pictureNumber="post.pictureNumber" />
          </div>
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
export default {
  name: "AllImage",
  data: function () {
    return {
      postList: [],
    };
  },
  components: {
    LikeButton,
    ReportButton,
    FavoriteButton,
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
  mounted() {
    EventBus.$on("search", (x) => {
      let self = this;
      this.postList = [];
      for (let i = 0; i < x.data.pictureNumberList.length; i++) {
        if (x.data.pictureObject[i].userId != null) {
          self.postList.push({
            postId: i,
            pictureNumber: x.data.pictureNumberList[i],
            userEmail: x.data.pictureObject[i].userId.userEmail,
            userNumber: x.data.pictureObject[i].userId.userNumber,
            img: x.data.img[i],
          });
        } else {
          self.postList.push({
            postId: i,
            pictureNumber: x.data.pictureNumberList[i],
            img: x.data.img[i],
          });
        }
      }
    });
  },
  created() {
    let self = this;
    this.$axios
      .get("http://localhost:80/all-pictures")
      .then((res) => {
        res.data.pictureObject.sort(function (a, b) {
          return a.pictureNumber - b.pictureNumber;
        });
        res.data.pictureNumberList.sort(function (a, b) {
          return a - b;
        });
        console.log(res.data);
        for (let i = 0; i < res.data.pictureNumberList.length; i++) {
          if (res.data.pictureObject[i].userId != null) {
            self.postList.push({
              postId: i,
              pictureNumber: res.data.pictureNumberList[i],
              userEmail: res.data.pictureObject[i].userId.userEmail,
              userNumber: res.data.pictureObject[i].userId.userNumber,
              img: res.data.img[i],
            });
          } else {
            self.postList.push({
              postId: i,
              pictureNumber: res.data.pictureNumberList[i],
              img: res.data.img[i],
            });
          }
        }
      })
      .catch();
  },
};
</script>
<style scoped>
img {
  display: block;
}
.container {
  max-width: 70%;
  margin: 0 auto;
  padding: 0 2rem;
}

/* Gallery Section */
.gallery {
  display: flex;
  flex-wrap: wrap;
}
.gallery-item {
  position: relative;
  cursor: pointer;
}

.gallery-item:hover .gallery-item-info,
.gallery-item:focus .gallery-item-info {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  top: -50%;
  width: 100%;
  height: 15%;
}
.gallery-item:hover {
  transform: scale(1.05);
}
.gallery-item-info {
  display: none;
}
.gallery-item-type {
  position: absolute;
  top: 10rem;
  right: 1rem;
  font-size: 2.5rem;
  text-shadow: 0.2rem 0.2rem 0.2rem rgba(0, 0, 0, 0.1);
}
.gallery-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
@supports (display: grid) {
  .gallery {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
    grid-gap: 2rem;
  }
  .gallery-item,
  .gallery {
    width: auto;
    margin: 0;
  }
}
</style>