<template>
  <div id="followButton" v-if="!check">
    <button type="button" v-on:click="Follow">Follow</button>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "FollowButton",
    data : function(){
        return {
            token : storage.getItem("jwt-auth-token"),
            check : this.$axios
        .get("http://localhost:80/users/follow/check/" +stroage.getItem(userNumber)+"/"+document.getElementById("userEmail").value, {
          params: {
            userEmail: document.getElementById("userEmail").value,
          },
        })
        .then((res) => {
          storage.setItem("userNumber", res.data.data["userNumber"]);
        }).catch()
        }
    },
    methods : {
        Follow : function(){
            if(token == null || token.length == 0) {
                alert("로그인을 해주세요.")
            } else {

            }
            
            
        },
        UnFollow : function(){
        },

    },
   
}
</script>