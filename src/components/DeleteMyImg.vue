<template>
  <div id="DeleteMyImg">
    <i
      class="fa fa-trash"
      style="position:relative;left:1px;font-size:30px;color:#8C8C8C"
      type="button"
      v-on:click="DeleteImg"
      v-if="myUserNumber == userNumber && token != null && token.length != 0 && token != 'undefined'"
    ></i>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "deleteMyImg",
  data: function () {
    return {
      token: storage.getItem("jwt-auth-token"),
    };
  },
  props: ["pictureNumber"],
  methods: {
    DeleteImg: function () {
      if (confirm("Do you want to delete your Img?") == true) {
        this.$axios(
          {
            method: "delete",
            url: "http://localhost:80/pictures/" + this.pictureNumber,
          },
          this.$router.replace("/mypage"),
          this.$router.go("/")
        );
      }
    },
  },
  created() {
    this.userNumber = storage.getItem("otherUserNumber");
    this.myUserNumber = storage.getItem("userNumber");
  },
};
</script>
<style scoped>
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url("https://fonts.googleapis.com/css?family=Raleway:200");
</style>