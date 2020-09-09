<template>
  <div id="SNSupdate">
    <input type="text" id="introduction" v-bind:placeholder="introduction" />
    <input type="text" id="snsLink1" v-bind:placeholder="snsLink1" />
    <input type="text" id="snsLink2" v-bind:placeholder="snsLink2" />
    <input type="text" id="snsLink3" v-bind:placeholder="snsLink3" />
    <button type="button" v-on:click="Update">Update</button>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "SNSupdate",
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
        method: "put",
        url: "http://localhost:80/users/" + storage.getItem("userNumber") + "/profileUpdate",
        params: {
          introduction: document.getElementById("introduction").value,
          snsLink1: document.getElementById("snsLink1").value,
          snsLink2: document.getElementById("snsLink2").value,
          snsLink3: document.getElementById("snsLink3").value,
        },
      })
        .then((res) => {console.log(res.data)
        this.$router.push("/mainpage");
        this.$router.go("/");
        })
        .catch(() => {});
    },
  },
};
</script>