<template>
  <div id="ShowUserEmail" class="showUserEmail-container">{{ userEmail }}</div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "ShowUserEmail",
  data() {
    return {
      userEmail: storage.getItem("otherUserEmail"),
    };
  },
};
</script>
<style>
.showUserEmail-container {
  position: relative;
  left: 35%;
  font-family: "NanumSquare", sans-serif;
  font-size: inherit;
  border: 0;
  background: transparent;
  width: 30%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
}
@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}
</style>