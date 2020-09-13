<template>
  <div id="followButton">
    <button
      type="button"
      v-on:click="Followlink"
      v-if="toUserEmail!=userEmail && !followCheck"
    >Follow</button>
    <button
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
          "http://127.0.0.1:80/follow/" +
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
          "http://127.0.0.1:80/follow/" +
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
        "http://127.0.0.1:80/follow/check/" +
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