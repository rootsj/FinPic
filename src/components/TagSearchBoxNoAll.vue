  
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
	</div>
</template>

<script>
  import EventBus from "../EventBus/EventBus.js"
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
            EventBus.$emit("search",self.imgSrc)
            console.log(res.data);
          })
          .catch();
      },
    },
  };
</script>