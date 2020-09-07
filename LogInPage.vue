<template>
    <div id = "logInPage">
        <input type = "text" id = "userEmail" v-bind:placeholder = "email"><br>
        <input type = "password" id = "userPassword" v-bind:placeholder = "password"><br>
        <button type = "button" v-on:click = "req()">Log In</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "LogInPage",
    data : function(){
        return {
            email : "User Email",
            password : "Password",
        }
    },
    methods: {
        req : function(){
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
          console.log(res.data.data);
          console.log(storage.getItem("userNumber"));

          //redirect를 위한 부분인데 다른 방법은 없을까?
          this.$router.push("/mainpage");
          this.$router.go("/");
        })
        .catch();
        }
    },
}
</script>