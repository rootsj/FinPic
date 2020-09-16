<template>
  <div id="favoriteButton">
    <i
      class="fa fa-heart"
      style="font-size:30px;color:white"
      v-bind:class="checked(add)"
      v-if="token != null && token.length != 0 && token != 'undefined'"
      v-on:click="favoriteAdd(add)"
    ></i>
    <!-- <button type = "button" v-bind:class="checked(add)" v-if="token != null && token.length != 0 && token != 'undefined'" v-on:click = "favoriteAdd(add)">Favorites</button> -->
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "FavoriteButton",
  props: ["pictureNumber"],
  data: function () {
    return {
      add: "",
      token: storage.getItem("jwt-auth-token"),
    };
  },
  created: function () {
    let self = this;
    console.log("FAVORITE BUTTON CREATED" + self.pictureNumber);
    this.$axios
      .get(
        "http://localhost:80/favorite/" +
          storage.getItem("userNumber") +
          "/check/" +
          self.pictureNumber
      )
      .then((res) => {
        self.add = res.data;
      })
      .catch();
  },
  methods: {
    checked: function (add) {
      if (add) {
        return { checked: true }; //추가->버튼 색깔 바꾸기.
      } else {
        return { checked: false }; //추가취소->버튼 색깔 바꾸기
      }
    },
    favoriteAdd: function (add) {
      if (add) {
        //true이면 삭제
        let self = this;
        this.$axios({
          method: "delete",
          url:
            "http://localhost:80/favorite/" +
            storage.getItem("userNumber") +
            "/" +
            self.pictureNumber,
        }).then((res) => {
          self.add = res.data;
        });
      } else {
        //false이면 추가
        let self = this;
        this.$axios
          .post(
            "http://localhost:80/favorite/" +
              storage.getItem("userNumber") +
              "/" +
              self.pictureNumber
          )
          .then((res) => {
            self.add = res.data;
          });
      }
    },
  },
  watch: {
    pictureNumber: function () {
      let self = this;
      console.log("FAVORITE BUTTON CREATED" + self.pictureNumber);
      this.$axios
        .get(
          "http://localhost:80/favorite/" +
            storage.getItem("userNumber") +
            "/check/" +
            self.pictureNumber
        )
        .then((res) => {
          self.add = res.data;
        })
        .catch();
    },
  },
};
</script>
<style scoped>
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url("https://fonts.googleapis.com/css?family=Raleway:200");
.checked::before {
  color: #ed0000;
}
</style>