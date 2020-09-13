<template>
  <div id="DelProfileImgBtn">
<<<<<<< HEAD
    <button type="button" v-on:click="DelBtn">Delete Profile Imgage</button>
=======
    <button class="DelProfileImgButton" type="button" v-on:click="DelBtn">DEL PROFILE IMG</button>
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
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
<<<<<<< HEAD
</script>
=======
</script>
<style>
.DelProfileImgButton {
  width: 100%;
  height: 30px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: 15px;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: white;
  background-color: #323232;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
}
.DelProfileImgButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare_0";
  src: url("./../assets/NanumSquare.ttf");
}
</style>
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
