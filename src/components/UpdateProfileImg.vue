<template>
  <div id="UpdateProfileImg">
    <form method="put" enctype="multipart/form-data">
      <div class="filebox">
        <label for="selectFile">{{selectBox}}</label>
        <input
          id="selectFile"
          type="file"
          enctype="multipart/form-data"
          v-on:change="fileSelect()"
          name="photo"
          ref="photoimage"
        />
      </div>
    </form>
    <div id="UpdateBtn">
      <button
        class="updateProImgButton"
        v-if="boolvalue"
        type="button"
        v-on:click="UpdateBtn()"
      >UPDATE PROFILE IMG</button>
    </div>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "UpdateProfileImg",
  data() {
    return {
      proImg: "",
      boolvalue: false,
      selectBox: "SELECT YOUR IMG ※ONLY .jpg .png .gif less than 10MB",
    };
  },
  methods: {
    fileSelect: function () {
      this.selectBox = document.getElementById("selectFile").value;
      this.proImg = this.$refs.photoimage.files[0];
      var fileCheck = document.getElementById("selectFile").value;
      if (fileCheck) {
        this.boolvalue = true;
      }
    },
    UpdateBtn: function () {
      this.$axios.put(
        "http://localhost:80/users/" +
          storage.getItem("userEmail") +
          "/profileImageDel"
      );
      const formData = new FormData();
      formData.append("img", this.proImg);
      this.$axios
        .put(
          "http://localhost:80/users/" +
            storage.getItem("userEmail") +
            "/profileImageUpdate",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then(() => {
          this.$router.push("/mypage");
          this.$router.go("/");
        })
        .catch(() => {});
    },
  },
};
</script>
<style>
.filebox {
  position: relative;
  top: 10px;
}
.filebox label {
  width: 96.5%;
  font-family: "NanumSquare_acL", sans-serif;
  display: inline-block;
  padding: 0.5em 0.75em;
  font-size: inherit;
  letter-spacing: 2.5px;
  font-weight: 300;
  color: white;
  line-height: normal;
  vertical-align: middle;
  background-color: #323232;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  outline: none;
}

.filebox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

.updateProImgButton {
  width: 100%;
  height: 30px;
  font-family: "NanumSquare", sans-serif;
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
  position: relative;
  top: 20px;
}
.updateProImgButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}
@font-face {
  font-family: "NanumSquare_acL";
  src: url("./../assets/NanumSquare_acL.ttf");
}
</style>