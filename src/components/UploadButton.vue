<template>
    <div id = "signUpButton" v-if="token != null && token.length != 0 && token != 'undefined'" >
        <button type = "button" v-on:click = "UploadPageLink">Upload</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "UploadButton",
    methods : {
        UploadPageLink : function(){
            this.$router.push("/uploadpage")
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