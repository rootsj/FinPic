<template>
  <div id="favoriteListButton">
    <button type="button" v-on:click="favoriteReq" v-if="userEmail==otherUserEmail">즐겨찾기</button>
  </div>
</template>
<script>
import EventBus from "../EventBus/EventBus.js";
const storage = window.sessionStorage;

export default {
  name: "FavoriteListButton",
  data: function () {
    return {
      userEmail: storage.getItem("userEmail"),
      otherUserEmail: storage.getItem("otherUserEmail"),
    };
  },
  methods: {
    favoriteReq: function () {
      EventBus.$emit("favorite-req");
    },
  },
};
</script>