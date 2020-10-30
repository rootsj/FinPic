<template>
  <div id="logInButton" v-if="token == null || token.length == 0 || token == 'undefined'">
    <button class="button" type="button" v-on:click="LogInPageLink">Log In</button>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "LogInButton",
  methods: {
    LogInPageLink: function () {
      this.$router.push("/loginpage");
      this.$router.go("/");
    },
  },
  data: function () {
    return {
      token: storage.getItem("jwt-auth-token"),
    };
  },
};
</script>
<style scoped>
.logInButton {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.button {
  width: 90px;
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