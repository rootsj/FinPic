<template>
  <div id="UpdateSNSinfo">
    <input type="text" id="introduction" v-bind:placeholder="introduction" />
    <br />
    <input type="text" id="snsLink1" v-bind:placeholder="snsLink1" />
    <br />
    <input type="text" id="snsLink2" v-bind:placeholder="snsLink2" />
    <br />
    <input type="text" id="snsLink3" v-bind:placeholder="snsLink3" />
    <br />
    <button type="button" v-on:click="Update">Update</button>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "UpdateSNSinfo",
  data() {
    return {
      introduction: "introduction",
      snsLink1: "SNSlink1",
      snsLink2: "SNSlink2",
      snsLink3: "SNSlink3",
    };
  },
  methods: {
    Update() {
      this.$axios({
        url:
          "http://localhost:80/users/" +
          storage.getItem("userNumber") +
          "/profileUpdate",
        method: "put",
        params: {
          introduction: document.getElementById("introduction").value,
          snsLink1: document.getElementById("snsLink1").value,
          snsLink2: document.getElementById("snsLink2").value,
          snsLink3: document.getElementById("snsLink3").value,
        },
      })
        .then(() => {
          console.log(document.getElementById("introduction").value);
          console.log(document.getElementById("snsLink1").value);
          this.$router.push("/mypage");
          this.$router.go("/");
        })
        .catch();
    },
  },
};
</script>