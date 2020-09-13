<template>
  <div id="UpdateInfo" class="updateInfoinput-container">
    <div>
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
    </div>
    <div>
      <button
        class="updateInfoButton"
        type="button"
        v-on:click="req()"
        v-bind:disabled="(Password != confirmPassword) || (Password.length == 0 || confirmPassword.length == 0 || nickNameModel.length == 0)"
      >UPDATE</button>
    </div>
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
<style>
.updateInfoinput-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 100%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.updateInfoinput-container input:focus {
  border: none;
  outline: none;
  border-bottom: 1px solid #673fbf;
}
.updateInfoButton {
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
  position: relative;
  top: 10px;
}
.updateInfoButton:hover {
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