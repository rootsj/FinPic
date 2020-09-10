<template>
  <div id="UpdateInfo">
    <input type="text" id="userName" v-bind:placeholder="nickName" v-model="nickNameModel" />
    <br />
    <input type="password" id="userPassword" v-bind:placeholder="userPassword" v-model="Password" />
    <br />
    <input
      type="password"
      id="confirmUserPassword"
      v-bind:placeholder="confirmUserPassword"
      v-model="confirmPassword"
    />
    <br />
    <button
      type="button"
      v-on:click="req()"
      v-bind:disabled="(Password != confirmPassword) || (Password.length == 0 || confirmPassword.length == 0 || nickNameModel.length == 0)"
    >Update</button>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "UpdateInfo",
  data() {
    return {
      nickName: "User NickName",
      nickNameModel: "",
      userPassword: "User Password",
      Password: "",
      confirmUserPassword: "Confirm User Password",
      confirmPassword: "",
    };
  },
  methods: {
    UpdateInfoBtn() {
      this.$axios({
        url: "http://localhost:80/users/" + storage.getItem("userNumber"),
        method: "put",
        params: {
          userName: document.getElementById("userName").value,
          userPassword: document.getElementById("userPassword").value,
        },
      })
        .then(() => {
          this.$router.push("/mypage");
          this.$router.go("/");
        })
        .catch();
    },
  },
};
</script>