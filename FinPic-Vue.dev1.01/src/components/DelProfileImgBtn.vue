<template>
  <div id="DelProfileImgBtn">
    <button type="button" v-on:click="DelBtn">Delete Profile Imgage</button>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "DelProfileImgBtn",
  methods: {
    DelBtn: function () {
      this.$axios.put(
        "http://localhost:80/users/" +
          storage.getItem("userEmail") +
          "/profileImageDel"
      );
      this.$router.push("/mypage");
      this.$router.go("/");
    },
  },
};
</script>