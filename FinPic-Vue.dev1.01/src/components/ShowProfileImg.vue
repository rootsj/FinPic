<template>
  <div id="ShowProfileImg">
<<<<<<< HEAD
    <img v-if="!img" src="./../assets/baseImg.png" width="175" v-on:click = "mypage()"/>
    <img v-if="img" v-bind:src="img" width="175" v-on:click = "mypage()"/>
=======
    <img
      class="ShowProfileImg"
      v-if="!img"
      src="./../assets/baseImg.png"
      width="175"
      v-on:click="mypage()"
    />
    <img v-if="img" v-bind:src="img" width="175" v-on:click="mypage()" />
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
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
<<<<<<< HEAD
  methods : {
    mypage : function(){
      this.$router.replace("/mypage");
      this.$router.go("/");
    }
=======
  methods: {
    mypage: function () {
      this.$router.replace("/mypage");
      this.$router.go("/");
    },
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
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
<<<<<<< HEAD

=======
<style>
.ShowProfileImg {
  width: 30%;
}
</style>
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
