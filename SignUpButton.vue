<template>
    <div id = "signUpButton" v-if="token == null || token.length == 0" >
        <button type = "button" v-on:click = "SignUpPageLink">Sign Up</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "SignUpButton",
    methods : {
        SignUpPageLink : function(){
            this.$router.push("/signuppage")
            this.$router.go("/")
        }
    },
    data : function(){
        return {
            token : storage.getItem("jwt-auth-token")
        }
    },

}
</script>