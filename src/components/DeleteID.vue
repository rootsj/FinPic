<template>
  <div id="DeleteID">
    <button class="DelButton" type="button" v-on:click="DeleteAction">Del ID</button>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "DeleteID",
  methods: {
    DeleteAction: function () {
      if (confirm("Do you want to delete your ID?") == true) {
        this.$axios
          .delete(
            "http://localhost:80/users/" + storage.getItem("userNumber")
          )
          .then(() => {
            storage.setItem("jwt-auth-token", "");
            storage.setItem("userNumber", "");
            this.$router.push("/mainpage");
            this.$router.go("/");
          });
      }
    },
  },
  data: function () {
    return {
      token: storage.getItem("jwt-auth-token"),
    };
  },
};
</script>
<style>
.DelButton {
  width: 100%;
  height: 30px;
  font-family: "NanumSquare", sans-serif;
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
.DelButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}
</style>