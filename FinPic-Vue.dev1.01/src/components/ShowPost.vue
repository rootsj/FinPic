<template>
<<<<<<< HEAD
  <div id="ShowPost"></div>
</template>
<script>
export default {
  name: "ShowPost",
  created() {},
};
</script>
=======
  <div id="ShowPost">
    <div id="show">
      <img class="imgStyle" v-bind:src="img" />
      <LikeButton :pictureNumber="pictureNumber" />
      <ReportButton :pictureNumber="pictureNumber" />
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
  created() {
    let self = this;
    this.$axios
      .get("http://localhost:80/showpost/" + storage.getItem("pictureNumber"))
      .then((res) => {
        self.img = res.data.img;
        self.tags = res.data.tags;
        console.log(self.img);
      });
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
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
