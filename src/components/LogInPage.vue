<template>
  <div id="logInPage">
    <div class="logInPageInput-container">
      <input type="text" id="userEmail" v-bind:placeholder="email" />
      <br />
      <br />
      <input type="password" id="userPassword" v-bind:placeholder="password" />
    </div>
    <br />
    <div class="btndiv">
      <button class="button" type="button" v-on:click="req()">Log In</button>
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "LogInPage",
  data: function () {
    return {
      email: "User Email",
      password: "Password",
    };
  },
  methods: {
    req: function () {
      this.$axios
        .get("http://localhost:80/users/login", {
          params: {
            userEmail: document.getElementById("userEmail").value,
            userPassword: document.getElementById("userPassword").value,
          },
        })
        .then((res) => {
          storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
          storage.setItem("userNumber", res.data.data["userNumber"]);
          storage.setItem("userEmail", res.data.data["userEmail"]);
          //redirect를 위한 부분인데 다른 방법은 없을까?
          this.$router.push("/mainpage");
          this.$router.go("/");
        })
        .catch();
    },
  },
};
</script>
<style>
.logInPageInput-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 40%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.logInPageInput-container input:focus {
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