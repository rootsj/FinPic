<template>
  <div id="signUp">
    <input type="text" id="userName" v-bind:placeholder="nickName" v-model="nickNameModel" />
    <br />
    <input type="text" id="userEmail" v-bind:placeholder="email" v-model="emailModel" />
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
      v-bind:disabled="(Password != confirmPassword) || (Password.length == 0 || confirmPassword.length == 0 || emailModel.length == 0 || nickNameModel.length == 0)"
    >Sign Up</button>
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
        url: "http://127.0.0.1:80/users",
        data: {
          userName: document.getElementById("userName").value,
          userEmail: document.getElementById("userEmail").value,
          userPassword: document.getElementById("userPassword").value,
        },
      })
        .then((res) => {console.log(res.data)
        //redirect 하는 곳 조건식 추가 할 것임
        this.$router.push("/mainpage");
        this.$router.go("/");
        })
        .catch(() => {});
    },

  },
};
</script>