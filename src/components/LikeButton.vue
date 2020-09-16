<template>
  <div id="likeButton">
    <i
      class="fa fa-thumbs-up"
      style="position:relative;right:3px;font-size:30px;color:white"
      v-on:click="like()"
      v-if="toggle && token != null && token.length != 0 && token != 'undefined'"
    ></i>

    <i
      class="fa fa-thumbs-up"
      style="position:relative;right:3px;font-size:30px;color:#0500A5"
      v-on:click="like()"
      v-if="!toggle && token != null && token.length != 0 && token != 'undefined'"
    ></i>

    <!-- <button type = "button" v-on:click = "like()" v-if="toggle && token != null && token.length != 0 && token != 'undefined'">like</button> -->
    <!-- <button type = "button" v-on:click = "like()" v-if="!toggle && token != null && token.length != 0 && token != 'undefined'">dislike</button> -->
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "LikeButton",
  data: function () {
    return {
      toggle: "",
      token: storage.getItem("jwt-auth-token"),
    };
  },
  props: ["pictureNumber"],
  methods: {
    like: function () {
      console.log(this.pictureNumber);
      let self = this;
      this.$axios
        .put(
          "http://localhost:80/like/" +
            storage.getItem("userNumber") +
            "/" +
            this.pictureNumber
        )
        .then((res) => {
          self.toggle = res.data;
        });
    },
  },
  watch: {
    pictureNumber: function () {
      let self = this;
      console.log("LIKE BUTTON CREATED" + this.pictureNumber);
      this.$axios
        .get(
          "http://localhost:80/like/verify/" +
            storage.getItem("userNumber") +
            "/" +
            this.pictureNumber
        )
        .then((res) => {
          self.toggle = res.data;
        })
        .catch();
    },
  },
  created: function () {
    let self = this;
    console.log("LIKE BUTTON CREATED" + this.pictureNumber);
    this.$axios
      .get(
        "http://localhost:80/like/verify/" +
          storage.getItem("userNumber") +
          "/" +
          this.pictureNumber
      )
      .then((res) => {
        self.toggle = res.data;
      })
      .catch();
  },
};
</script>
<style scoped>
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url("https://fonts.googleapis.com/css?family=Raleway:200");
</style>