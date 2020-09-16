<template>
  <div id="followListButton">
    <button
      class="followListButton"
      type="button"
      v-on:click="followSearch"
      v-if="toUserEmail==userEmail"
    >FollowList({{followCount}})</button>
    <div class="followListContainer">
      <div class="wrapper">
        <div
          class="followProfile"
          id="show"
          v-for="follow in followList"
          v-bind:key="follow.followId"
        >
          <i
            class="userBtn"
            type="button"
            v-on:click="followPage(follow.followEmail, follow.followNumber)"
            v-if="toggleFollowList"
          >
            <img class="pageImg" v-bind:src="follow.followImg" />
          </i>
          <div class="userInfo" v-if="toggleFollowList">
            {{follow.followName}}
            <br />
            {{follow.followEmail}}
            <br />
            {{follow.followNumber}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;
import EventBus from "../EventBus/EventBus.js";

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
  props: ["toggleFollowList"],
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
          self.toggleFollowList = !self.toggleFollowList;
          EventBus.$emit("followToggle", false);
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
      .get("http://localhost:80/follow/count/" + self.userNumber)
      .then((res) => {
        console.log(res.data);
        self.followCount = res.data;
      })
      .catch();
  },
};
</script>
<style scoped>
.followListContainer {
  position: absolute;
  width: 120%;
  left: 0%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.wrapper {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
}
.followProfile {
  max-width: 25%;
  margin: 2%;
  margin-bottom: 5%;
  transition: 0.15s all ease-in-out;
}

.pageImg {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 30px;
  -moz-border-radius: 30px;
  -khtml-border-radius: 30px;
  -webkit-border-radius: 30px;
}
.pageImg:hover {
  transform: scale(1.05);
}
.userInfo {
  position: relative;
  left: 0%;
  font-family: "NanumSquare";
  color: white;
}
@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}

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