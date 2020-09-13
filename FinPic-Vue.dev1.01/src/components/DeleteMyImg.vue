<template>
  <div id="DeleteMyImg">
    <button
      type="button"
      v-on:click="DeleteImg"
      v-if="token != null && token.length != 0 && token != 'undefined'"
    >Delete</button>
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
            url: "http://127.0.0.1:80/pictures/" + this.pictureNumber,
          },
          this.$router.replace("/mypage"),
          this.$router.go("/")
        );
      }
    },
  },
};
</script>