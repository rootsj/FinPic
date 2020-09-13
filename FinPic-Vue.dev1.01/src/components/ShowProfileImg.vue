<template>
  <div id="ShowProfileImg">
    <img
      class="ShowProfileImg"
      v-if="!img"
      src="./../assets/baseImg.png"
      width="175"
      v-on:click="mypage()"
    />
    <img class="ShowProfileImg" v-if="img" v-bind:src="img" width="175" v-on:click="mypage()" />
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
  methods: {
    mypage: function () {
      this.$router.replace("/mypage");
      this.$router.go("/");
    },
  },
  created() {
    this.$axios
      .get(
        "http://localhost:80/users/" +
          storage.getItem("otherUserEmail") +
          "/profileImg"
      )
      .then((res) => {
        this.img = res.data;
      });
  },
};
</script>
<style>
.ShowProfileImg {
  width: 30%;
}
</style>
