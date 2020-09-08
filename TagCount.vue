<template>
  <div name="tagSearchBox">
    <input type="text" v-bind:placeholder="tag" v-on:keyup.enter="search()"/>
    <button
        type="button"
        v-on:click="search()"
        v-bind:disabled="(tag.length == 0)">검색</button>
    <div id = "show" v-for="(img, index) in imSrc" v-bind:key = "index">
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
        imgSrc : "",
      };
    },
    methods: {
      search: function () {
        let self = this;
        this.$axios
          .get("http://localhost:80/pictures-and-tags/"+document.getElementById("tag"))
          .then((res) => {
            self.imgSrc = res.data
            console.log(res.data);
          })
          .catch();
      },

    },
  };
</script>