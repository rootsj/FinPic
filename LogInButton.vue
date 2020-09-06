<template>
    <div id = "logInButton" v-if="token == null || token.length == 0" >
        <button type = "button" v-on:click = "LogInPageLink">Log In</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "LogInButton",
    methods : {
        LogInPageLink : function(){
            this.$router.push("/log-in-page")
        }
    },
    data : function(){
        return {
            token : storage.getItem("jwt-auth-token")
        }
    },
}
</script>