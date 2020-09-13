<template>
  <div id="reportButton">
    <i
      type="button"
      v-on:click="report()"
      v-if="toggle && token != null && token.length != 0 && token != 'undefined'"
    >
      <img src="./../assets/grayCaution.png" width="40px" />
    </i>
    <i
      type="button"
      v-on:click="report()"
      v-if="!toggle && token != null && token.length != 0 && token != 'undefined'"
    >
      <img src="./../assets/caution.png" width="40px" />
    </i>
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