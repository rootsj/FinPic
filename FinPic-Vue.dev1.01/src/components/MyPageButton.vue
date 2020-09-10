<template>
    <div id = "myPageButton" v-if="token != null && token.length != 0 && token != 'undefined'">
        <button type = "button" v-on:click = "MyPageButton">MyPage</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "MyPageButton",
    data : function(){
        return {
            token : storage.getItem("jwt-auth-token")
        }
    },
    methods : {
        MyPageButton : function(){
            storage.setItem("otherUserNumber",storage.getItem("userNumber"));
            storage.setItem("otherUserEmail",storage.getItem("userEmail"));
            this.$router.push("/mypage");
            this.$router.go("/");
        }
    },
}
</script>