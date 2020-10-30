  
<template>
  <div name="tagSearchBox" class="input-container">
    <div class="btndiv">
      <input type="text" v-bind:placeholder="tag" v-on:keyup.enter="search()" v-model="tagName" />
      <button
        class="button"
        type="button"
        v-on:click="search()"
        v-bind:disabled="(tagName.length == 0)"
      >검색</button>
    </div>
    <div v-if="isActive">
      tag : {{tagName}}
      <br />
      amount of reulst :{{count}}
    </div>
    <div id="show" v-for="(img, index) in imgSrc" v-bind:key="index">
      <img v-bind:src="img" />
    </div>
  </div>
</template>


<script>
export default {
  name: "tagSearchBox",
  data: function () {
    return {
      tag: "태그를 검색하세요",
      tagName: "",
      imgSrc: "",
      count: "",
      isActive: false,
    };
  },
  methods: {
    search: function () {
      let self = this;
      this.isActive = true;
      this.$axios
        .get("http://localhost:80/pictures-and-tags/" + self.tagName)
        .then((res) => {
          self.imgSrc = res.data.img;
          self.count = res.data.img.length;
          console.log(res.data);
        })
        .catch();
    },
  },
  created() {
    let self = this;
    this.$axios.get("http://127.0.0.1:80/all-pictures").then((res) => {
      self.imgSrc = res.data.img;
      console.log(res.data.pictureObject[0].pictureNumber);
    });
  },
};
</script>
<style>
.input-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 40%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.input-container input:focus {
  border: none;
  outline: none;
  border-bottom: 1px solid #673fbf;
}

.btndiv {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.button {
  width: 100px;
  height: 40px;
  font-family: "NanumSquare_0", sans-serif;
  font-size: 15px;
  text-transform: uppercase;
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
}

.button:hover {
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