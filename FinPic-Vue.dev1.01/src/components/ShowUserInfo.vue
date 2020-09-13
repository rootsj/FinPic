<template>
  <div id="ShowUserInfo">
    <div class="showUserInfo-container">Introduction</div>
    <div class="showUserInfoContent">{{introduction }}</div>
    <div class="showUserInfo-container">Personal SNS</div>
    <div class="showUserInfoContent">
      <a href="snsLink1">{{snsLink1}}</a>
      <br />
      <a href="snsLink2">{{snsLink2}}</a>
      <br />
      <a href="snsLink3">{{snsLink3}}</a>
    </div>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "ShowUserInfo",
  data() {
    return {
      introduction: "",
      snsLink1: "",
      snsLink2: "",
      snsLink3: "",
    };
  },
  created() {
    let self = this;
    this.$axios
      .get(
        "http://localhost:80/users/" +
          storage.getItem("otherUserNumber") +
          "/profile"
      )
      .then((res) => {
        self.introduction = res.data.introduction;
        self.snsLink1 = res.data.snsLink1;
        self.snsLink2 = res.data.snsLink2;
        self.snsLink3 = res.data.snsLink3;
      });
  },
};
</script>
<style>
.showUserInfo-container {
  position: relative;
  left: 35%;
  font-family: "NanumSquare", sans-serif;
  font-size: inherit;
  border: 0;
  background: transparent;
  width: 30%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #673fbf;
}
.showUserInfoContent {
  position: relative;
  left: 35%;
  font-family: "NanumSquare_acL", sans-serif;
  font-size: inherit;
  border: 0;
  background: transparent;
  width: 30%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
  top: -10px;
}
@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}
@font-face {
  font-family: "NanumSquare_acL";
  src: url("./../assets/NanumSquare_acL.ttf");
}
</style>