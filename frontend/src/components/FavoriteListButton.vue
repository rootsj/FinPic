<template>
  <div id="favoriteListButton">
    <button
      class="favoriteListButton"
      type="button"
      v-on:click="favoriteReq"
      v-if="userEmail==otherUserEmail"
    >Favorite</button>
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
      EventBus.$emit("favoriteToggle", false);
    },
  },
};
</script>
<style>
.favoriteListButton {
  width: 30%;
  height: 25px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: inherit;
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
.favoriteListButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
</style>