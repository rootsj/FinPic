<template>
  <div id="ShowPost">
    <div id="show">
      <img class="imgStyle" v-bind:src="img" />
      <LikeButton :pictureNumber="pictureNumber" v-if="img" />
      <ReportButton :pictureNumber="pictureNumber" v-if="img" />
      {{tags}}
    </div>
  </div>
</template>
<script>
const storage = window.sessionStorage;

import LikeButton from "./LikeButton.vue";
import ReportButton from "./ReportButton.vue";
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
  },
  mounted() {
    let self = this;
    this.$axios
      .get("http://localhost:80/showpost/" + storage.getItem("pictureNumber"))
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
<style>
.imgStyle {
  width: 100%;
}
</style>