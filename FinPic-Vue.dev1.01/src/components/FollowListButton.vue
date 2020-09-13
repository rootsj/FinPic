<template>
  <div id="followListButton">
    <button
      class="followListButton"
      type="button"
      v-on:click="followSearch"
      v-if="toUserEmail==userEmail"
    >FollowList({{followCount}})</button>
    <div id="show" v-for="follow in followList" v-bind:key="follow.followId">
      <button
        class="followListButton"
        type="button"
        v-on:click="followPage(follow.followEmail, follow.followNumber)"
      >
        <img v-bind:src="follow.followImg" />
        {{follow.followName}} {{follow.followEmail}} {{follow.followNumber}}
      </button>
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "followListButton",
  data: function () {
    return {
      userNumber: storage.getItem("userNumber"),
      userEmail: storage.getItem("userEmail"),
      toUserEmail: storage.getItem("otherUserEmail"),
      followList: [],
      followCount: "",
    };
  },
  methods: {
    followSearch: function () {
      let self = this;
      this.$axios
        .get("http://localhost:80/follow/follow/" + self.userNumber)
        .then((res) => {
          self.followList = [];
          for (let i = 0; i < res.data.data.length; i++) {
            self.followList.push({
              followId: i,
              followName: res.data.data[i].toUser.userName,
              followNumber: res.data.data[i].toUser.userNumber,
              followEmail: res.data.data[i].toUser.userEmail,
              followImg: res.data.img[i],
            });
          }
          console.log(self.followList);
        })
        .catch();
    },

    followPage: function (y, z) {
      storage.setItem("otherUserEmail", y);
      storage.setItem("otherUserNumber", z);

      this.$router.push("/mypage");
      this.$router.go("/");
    },
  },
  mounted() {
    let self = this;
    this.$axios
      .get("http://127.0.0.1:80/follow/count/" + self.userNumber)
      .then((res) => {
        console.log(res.data);
        self.followCount = res.data;
      })
      .catch();
  },
};
</script>
<style>
.followListButton {
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
.followListButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
</style>