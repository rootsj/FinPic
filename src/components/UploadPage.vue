<template>
  <div id="uploadPage">
    <div class="UploadPageInput-container" id="picture_tags">
      <input type="text" id="tagName" v-bind:placeholder="tag" />
      <br />
    </div>
    <br />

    <form method="post" enctype="multipart/form-data">
      <div class="filebox">
        <label for="selectFile">{{selectBox}}</label>
        <input
          id="selectFile"
          type="file"
          enctype="multipart/form-data"
          v-on:change="fileSelect"
          name="photo"
          ref="photoimage"
        />
      </div>
    </form>
    <div id="preImg">
      <img class="preImg" v-if="imageUrl" :src="imageUrl" />
    </div>
    <div id="uploadButton">
      <button class="UploadPageBtn" type="button" v-on:click="req()">FinPic UPLOAD</button>
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "UploadPage",
  data: function () {
    return {
      tag: "ENTER YOUR TAG BY DIVIDING BY '#'",
      selectBox: "SELECT YOUR FinPic ※ ONLY .jpg .png .gif less than 10MB",
      uploadImageFile: "",
      imageUrl: null,
    };
  },
  methods: {
    fileSelect: function (e) {
      if (document.getElementById("selectFile").value) {
        this.selectBox = document.getElementById("selectFile").value;
      } else {
        this.selectBox =
          "SELECT YOUR FinPic ※ ONLY .jpg .png .gif less than 10MB";
      }
      this.img = this.$refs.photoimage.files[0];
      console.log(e.target.files);
      const file = e.target.files[0];
      this.imageUrl = URL.createObjectURL(file);
    },

    req: function () {
      const formData = new FormData();
      formData.append("img", this.img);
      formData.append("tagName", document.getElementById("tagName").value);
      this.$axios
        .post(
          "http://localhost:80/pictures-and-tags/" +
            storage.getItem("userNumber"),
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          this.$router.push("/mainpage");
          this.$router.go("/");
        })
        .catch(() => {});
    },
  },
};
</script>
<style>
.preImg {
  width: 40%;
  position: relative;
  top: 10px;
}
.filebox label {
  width: 39.5%;
  font-family: "NanumSquare_acL", sans-serif;
  display: inline-block;
  padding: 0.5em 0.75em;
  font-size: 7px;
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
.UploadPageInput-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 40%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.UploadPageInput-container input:focus {
  border: none;
  outline: none;
  border-bottom: 1px solid #673fbf;
}

.UploadPageBtn {
  width: 40%;
  height: 30px;
  font-family: "NanumSquare", sans-serif;
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
  top: 20px;
}
.UploadPageBtn:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(103, 63, 191, 0.4);
  color: #323232;
  transform: translateY(-7px);
}
@font-face {
  font-family: "NanumSquare";
  src: url("./../assets/NanumSquare.ttf");
}
</style>