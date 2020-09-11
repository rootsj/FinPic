<template>
  <div id="UpdateProfileImg">
    <form method="put" enctype="multipart/form-data">
      <input
        type="file"
        enctype="multipart/form-data"
        v-on:change="fileSelect()"
        id="exsist"
        name="photo"
        ref="photoimage"
      />
    </form>
    <div id="UpdateBtn">
      <button v-if="boolvalue" type="button" v-on:click="UpdateBtn()">Update Profile Image</button>
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
    };
  },
  methods: {
    fileSelect: function () {
      this.proImg = this.$refs.photoimage.files[0];
      var fileCheck = document.getElementById("exsist").value;
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