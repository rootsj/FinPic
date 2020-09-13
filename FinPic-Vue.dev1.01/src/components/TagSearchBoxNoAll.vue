  
<template>
  <div name="tagSearchBox" class="tagSearchBoxNoAllBtnInput-container">
    <input type="text" v-bind:placeholder="tag" v-on:keyup.enter="search()" v-model="tagName" />
    <button
      type="button"
      class="tagSearchBoxNoAllBtn"
      v-on:click="search()"
      v-bind:disabled="(tagName.length == 0)"
    >SEARCH</button>
    <div class="searchResult" v-if="isActive">The number of posts: {{count}}</div>
  </div>
</template>

<script>
import EventBus from "../EventBus/EventBus.js";
export default {
  name: "tagSearchBox",
  data: function () {
    return {
      tag: "Search your FinPic",
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
          res.data.pictureNumberList.sort(function (a, b) {
            return a - b;
          });
          res.data.pictureObject.sort(function (a, b) {
            return a.pictureNumber - b.pictureNumber;
          });
          self.imgSrc = res.data.img;
          self.count = res.data.img.length;
          EventBus.$emit("search", res);
        })
        .catch();
    },
  },
};
</script>
<style>
.tagSearchBoxNoAllBtnInput-container input {
  border: 0;
  border-bottom: 1px solid #555;
  background: transparent;
  width: 50%;
  padding: 8px 0 5px 0;
  font-size: 16px;
  color: #fff;
}
.tagSearchBoxNoAllBtnInput-container input:focus {
  border: none;
  outline: none;
  border-bottom: 1px solid #673fbf;
}
.tagSearchBoxNoAllBtn {
  width: 100px;
  height: 28px;
  font-family: "NanumSquare", sans-serif;
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

.tagSearchBoxNoAllBtn:hover {
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

.searchResult {
  position: relative;
  top: 5px;
  color: #eaeaea;
  font-family: "NanumSquare_acL", sans-serif;
}
</style>