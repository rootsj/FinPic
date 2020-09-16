<template>
  <div id="followButton">
    <button
      class="followButton"
      type="button"
      v-on:click="Followlink"
      v-if="toUserEmail!=userEmail && !followCheck"
    >Follow</button>
    <button
      class="followButton"
      type="button"
      v-on:click="UnFollowlink"
      v-if="toUserEmail!=userEmail && followCheck"
    >UnFollow</button>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "FollowButton",
  data: function () {
    return {
      userEmail: storage.getItem("userEmail"),
      toUserEmail: storage.getItem("otherUserEmail"),
      followCheck: false,
    };
  },
  methods: {
    Followlink: function () {
      let self = this;
      this.$axios({
        method: "post",
        url:
          "http://localhost:80/follow/" +
          storage.getItem("userNumber") +
          "/" +
          self.toUserEmail,
      })
        .then((res) => {
          self.followCheck = res.data;
        })
        .catch(() => {});
    },
    UnFollowlink: function () {
      let self = this;
      this.$axios({
        method: "delete",
        url:
          "http://localhost:80/follow/" +
          storage.getItem("userNumber") +
          "/" +
          self.toUserEmail,
      })
        .then((res) => {
          self.followCheck = res.data;
        })
        .catch(() => {});
    },
  },
  mounted() {
    let self = this;
    this.$axios
      .get(
        "http://localhost:80/follow/check/" +
          storage.getItem("userNumber") +
          "/" +
          self.toUserEmail
      )
      .then((res) => {
        self.followCheck = res.data;
      })
      .catch();
  },
};
</script>
<style scoped>
.followButton {
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
.followButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
</style>