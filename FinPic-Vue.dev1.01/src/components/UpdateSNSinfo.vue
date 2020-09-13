<template>
  <div id="UpdateSNSinfo" class="updateSNSinput-container">
    <div>
      <input type="text" id="introduction" v-bind:placeholder="introduction" />
      <br />
      <input type="text" id="snsLink1" v-bind:placeholder="snsLink1" />
      <br />

      <input type="text" id="snsLink2" v-bind:placeholder="snsLink2" />
      <br />
      <input type="text" id="snsLink3" v-bind:placeholder="snsLink3" />
    </div>
    <div>
      <button type="button" class="updateSNSButton" v-on:click="Update">UPDATE</button>
    </div>
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
<style>
.updateSNSinput-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 100%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.updateSNSinput-container input:focus {
  border: none;
  outline: none;
  border-bottom: 1px solid #673fbf;
}
.updateSNSButton {
  width: 100%;
  height: 30px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: 15px;
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
  position: relative;
  top: 10px;
}
.updateSNSButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare_0";
  src: url("./../assets/NanumSquare.ttf");
}
</style>