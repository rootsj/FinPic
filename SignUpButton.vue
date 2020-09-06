<template>
    <div id = "signUpButton" v-if="token != null" >
        <button type = "button" v-on:click = "SignUpPageLink">Sign Up</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "SignUpButton",
    methods : {
        SignUpPageLink : function(){
            this.$router.push("/sign-up-page")
        }
    },
    data : function(){
        return {
            token : storage.getItem("jwt-auth-token")
        }
    },

}
</script>