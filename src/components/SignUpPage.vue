<template>
  <div id="signUp">
    <div class="input-container">
      <input type="text" id="userName" v-bind:placeholder="nickName" v-model="nickNameModel" />
      <br />
      <br />
      <input type="text" id="userEmail" v-bind:placeholder="email" v-model="emailModel" />
      <br />
      <br />
      <input type="password" id="userPassword" v-bind:placeholder="userPassword" v-model="Password" />
      <br />
      <br />
      <input
        type="password"
        id="confirmUserPassword"
        v-bind:placeholder="confirmUserPassword"
        v-model="confirmPassword"
      />
    </div>
    <br />
    <br />
    <div class="btndiv">
      <button
        class="button"
        type="button"
        v-on:click="req()"
        v-bind:disabled="(Password != confirmPassword) || (Password.length == 0 || confirmPassword.length == 0 || emailModel.length == 0 || nickNameModel.length == 0)"
      >Sign Up</button>
    </div>
    <br />
  </div>
</template>

<script>
export default {
  name: "SignUpPage",
  data: function () {
    return {
      email: "User Email",
      emailModel: "",
      nickName: "User NickName",
      nickNameModel: "",
      userPassword: "User Password",
      Password: "",
      confirmUserPassword: "Confirm User Password",
      confirmPassword: "",
    };
  },
  methods: {
    req: function () {
      this.$axios({
        method: "post",
        url: "http://localhost:80/users",
        data: {
          userName: document.getElementById("userName").value,
          userEmail: document.getElementById("userEmail").value,
          userPassword: document.getElementById("userPassword").value,
        },
      })
        .then((res) => {
          console.log(res.data);
          //redirect 하는 곳 조건식 추가 할 것임
          this.$router.push("/mainpage");
          this.$router.go("/");
        })
        .catch(() => {});
    },
  },
};
</script>
<style>
.input-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 40%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.input-container input:focus {
  border: none;
  outline: none;
  border-bottom: 1px solid #673fbf;
}

.btndiv {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.button {
  width: 100px;
  height: 40px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: 15px;
  text-transform: uppercase;
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

.button:hover {
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