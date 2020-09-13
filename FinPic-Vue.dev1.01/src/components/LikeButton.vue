<template>
<<<<<<< HEAD
    <div id = "likeButton">
        <button type = "button" v-on:click = "like()" v-if="toggle && token != null && token.length != 0 && token != 'undefined'">like</button>
        <button type = "button" v-on:click = "like()" v-if="!toggle && token != null && token.length != 0 && token != 'undefined'">dislike</button>
    </div>
=======
  <div id="likeButton">
    <i
      class="btn_like"
      type="button"
      v-on:click="like()"
      v-if="toggle && token != null && token.length != 0 && token != 'undefined'"
    >
      <img src="./../assets/heart.png" width="45px" />
    </i>
    <i
      class="btn_like"
      type="button"
      v-on:click="like()"
      v-if="!toggle && token != null && token.length != 0 && token != 'undefined'"
    >
      <img src="./../assets/grayHeart.png" width="45px" />
    </i>
  </div>
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
</template>

<script>
const storage = window.sessionStorage;

export default {
<<<<<<< HEAD
    name : "LikeButton",
    data : function(){
        return {
            toggle : true,
            pictureNumber : "",
            token : storage.getItem("jwt-auth-token"),
        }
    },
    methods: {
        like : function(){
            let self = this;
            this.$axios.put("http://localhost:80/like/"+storage.getItem("userNumber")+"/"+this.pictureNumber)
            .then(res => {
                console.log(res);
                self.toggle = !self.toggle;
            })
        }
    },
}
=======
  name: "LikeButton",
  data: function () {
    return {
      toggle: "",
      token: storage.getItem("jwt-auth-token"),
    };
  },
  props: ["pictureNumber"],
  methods: {
    like: function () {
      let self = this;
      this.$axios
        .put(
          "http://localhost:80/like/" +
            storage.getItem("userNumber") +
            "/" +
            this.pictureNumber
        )
        .then((res) => {
          self.toggle = res.data;
        });
    },
  },
  created() {
    let self = this;
    this.$axios
      .get(
        "http://localhost:80/like/verify/" +
          storage.getItem("userNumber") +
          "/" +
          this.pictureNumber
      )
      .then((res) => {
        self.toggle = res.data;
      });
  },
};
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
</script>