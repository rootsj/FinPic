<template>
  <div id="ShowUserInfo">
    introduction
    {{introduction }}
    <br />
    <br />
    SNS link
    {{snsLink1}}
    {{snsLink2}}
    {{snsLink3}}
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "ShowUserInfo",
  data() {
    return {
      introduction: "",
      snsLink1: "",
      snsLink2: "",
      snsLink3: "",
    };
  },
  created() {
    let self = this;
    this.$axios
      .get(
        "http://localhost:80/users/" +
          storage.getItem("userNumber") +
          "/profile"
      )
      .then((res) => {
        self.introduction = res.data.introduction;
        self.snsLink1 = res.data.snsLink1;
        self.snsLink2 = res.data.snsLink2;
        self.snsLink3 = res.data.snsLink3;
      });
  },
};
</script>