<template>
  <div id="ShowProfileImg">
    <img v-if="!img" src="./../assets/baseImg.png" width="175" />
    <img v-if="img" v-bind:src="img" width="175" />
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "ShowProfileImg",
  data() {
    return {
      img: "",
    };
  },
  watch: {
    img: function () {
      if (!this.img) {
        this.img = "./../assets/baseImg.png";
        console.log(this.img);
      }
    },
  },
  created() {
    this.$axios
      .get(
        "http://localhost:80/users/" +
          storage.getItem("userEmail") +
          "/profileImg"
      )
      .then((res) => {
        this.img = res.data;
      });
  },
};
</script>

