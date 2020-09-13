<template>
  <div id="reportButton">
    <i
      class="fa fa-warning"
      style="position:relative;right:2px;font-size:30px;color:white"
      v-on:click="report()"
      v-if="toggle && token != null && token.length != 0 && token != 'undefined'"
    ></i>
    <i
      class="fa fa-warning"
      style="font-size:30px;color:#FFBB00"
      v-on:click="report()"
      v-if="!toggle && token != null && token.length != 0 && token != 'undefined'"
    ></i>
    <!-- <button type = "button" v-on:click = "report()" v-if="toggle && token != null && token.length != 0 && token != 'undefined'">report</button> -->
    <!-- <button type = "button" v-on:click = "report()" v-if="!toggle && token != null && token.length != 0 && token != 'undefined'">disreport</button> -->
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "ReportButton",
  data: function () {
    return {
      toggle: "",
      token: storage.getItem("jwt-auth-token"),
    };
  },
  props: ["pictureNumber"],
  methods: {
    report: function () {
      let self = this;
      this.$axios
        .put(
          "http://localhost:80/report/" +
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
      console.log(this.pictureNumber + "Report Button Created");
      this.$axios
        .get(
          "http://localhost:80/report/verify/" +
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
    console.log(this.pictureNumber + "Report Button Created");
    this.$axios
      .get(
        "http://localhost:80/report/verify/" +
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