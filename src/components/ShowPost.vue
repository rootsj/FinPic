<template>
  <div id="ShowPost">
    <div id="show" class="gallery-item">
      <img class="gallery-image" v-bind:src="img" />
      <div class="gallery-item-info">
        <LikeButton :pictureNumber="pictureNumber" v-if="img" />
        <ReportButton :pictureNumber="pictureNumber" v-if="img" />
        <FavoriteButton :pictureNumber="pictureNumber" v-if="img" />
      </div>
      <div class="tagBtn">{{tags}}</div>
    </div>
  </div>
</template>
<script>
const storage = window.sessionStorage;

import LikeButton from "./LikeButton.vue";
import ReportButton from "./ReportButton.vue";
import FavoriteButton from "./FavoriteButton.vue";
export default {
  name: "ShowPost",
  data: function () {
    return {
      img: "",
      tags: "",
      pictureNumber: storage.getItem("pictureNumber"),
    };
  },
  components: {
    LikeButton,
    ReportButton,
    FavoriteButton,
  },
  mounted() {
    let self = this;
    this.$axios
      .get(
        "http://localhost:80/showpost/" + storage.getItem("pictureNumber")
      )
      .then((res) => {
        self.img = res.data.img;
        self.tags = res.data.tags;
        console.log(self.img);
      });
    storage.setItem("pictureNumber", "");
    console.log(storage.getItem("pictureNumber"));
    console.log("ShowPost");
    console.log(self.img);
  },
};
</script>
<style scoped>
.gallery {
  display: flex;
  flex-wrap: wrap;
  margin: -1rem -1rem;
  padding-bottom: 3rem;
}
.gallery-item {
  position: relative;
  flex: 1 0 22rem;
  margin: 1rem;
  color: #fff;
  cursor: pointer;
}

.gallery-item:hover .gallery-item-info,
.gallery-item:focus .gallery-item-info {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 40%;
  width: 100%;
  height: 15%;
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
  object-fit: cover;
}

@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}
.tagBtn {
  width: 100%;
  height: 30px;
  font-family: "NanumSquare", sans-serif;
  font-size: 20px;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: white;
  background-color: #323232;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  outline: none;
  position: relative;
  top: 10px;
  padding-top: 10px;
}
</style>