  
<template>
  <div name="tagSearchBox">
    <input type="text" v-bind:placeholder="tag" v-on:keyup.enter="search()" v-model= "tagName"/>
    <button
        type="button"
        v-on:click="search()"
        v-bind:disabled="(tagName.length == 0)">검색</button>
      <div v-if="isActive"> 
      tag : {{tagName}}<br>
      amount of reulst :{{count}}
      </div>
    <div id = "show" v-for="(img, index) in imgSrc" v-bind:key = "index">
      <img v-bind:src="img">
    </div>
	</div>
</template>


<script>
  export default {
    name: "tagSearchBox",
    data: function () {
      return {
        tag: "태그를 검색하세요",
        tagName : "",
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
          .get("http://localhost:80/pictures-and-tags/"+self.tagName)
          .then((res) => {
            self.imgSrc = res.data.img;
            self.count = res.data.img.length;
            console.log(res.data);
          })
          .catch();
      },
    },
    created() {
      let self = this
      this.$axios.get("http://127.0.0.1:80/all-pictures")
        .then(res => {
        self.imgSrc = res.data.img
        console.log(res.data.pictureObject[0].pictureNumber)
        })
    }
  };
</script>