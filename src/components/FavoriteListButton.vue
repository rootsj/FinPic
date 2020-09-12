<template>
    <div id="favoriteListButton" >
    <button type = "button" v-on:click.once ="favoriteReq" v-if="userEmail==otherUserEmail">즐겨찾기</button>
    <FavoriteList/>
    </div>
</template>
<script>
import EventBus from "../EventBus/EventBus.js"
import FavoriteList from "./FavoriteList.vue"
const storage = window.sessionStorage;

export default {
    name: "FavoriteListButton",
    data : function(){
        return {
            userEmail: storage.getItem("userEmail"),
            otherUserEmail: storage.getItem("otherUserEmail")
        }
    },
    components : {
        FavoriteList
    },
    methods:{
        favoriteReq : function() {
            EventBus.$emit('favorite-req');
        }
    }
}
</script>