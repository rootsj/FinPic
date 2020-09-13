<template>
  <div id="favoriteButton">
    <button
      type="button"
      v-bind:class="checked(add)"
      v-if="token != null && token.length != 0 && token != 'undefined'"
      v-on:click="favoriteAdd(add)"
    >Favorites</button>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: "FavoriteButton",
  props: ["pictureNumber"],
  data: function () {
    return {
      add: "",
      token: storage.getItem("jwt-auth-token"),
    };
  },
  created: function () {
    let self = this;
    this.$axios
      .get(
        "http://127.0.0.1:80/favorite/" +
          storage.getItem("userNumber") +
          "/check/" +
          this.pictureNumber
      )
      .then((res) => {
        console.log(res.data);
        self.add = res.data;
      });
  },
  methods: {
    checked: function (add) {
      if (add) {
        return { checked: true }; //추가->버튼 색깔 바꾸기.
      } else {
        return { checked: false }; //추가취소->버튼 색깔 바꾸기
      }
    },
    favoriteAdd: function (add) {
      if (add) {
        //true이면 삭제
        let self = this;
        this.$axios({
          method: "delete",
          url:
            "http://127.0.0.1:80/favorite/" +
            storage.getItem("userNumber") +
            "/" +
            this.pictureNumber,
        }).then((res) => {
          self.add = res.data;
        });
      } else {
        //false이면 추가
        let self = this;
        this.$axios
          .post(
            "http://127.0.0.1:80/favorite/" +
              storage.getItem("userNumber") +
              "/" +
              this.pictureNumber
          )
          .then((res) => {
            self.add = res.data;
          });
      }
    },
  },
};
</script>
<style scoped>
button {
  border: none;
  color: white;
  padding: 8px 12px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  cursor: pointer;
  border-radius: 50%px;
  border: 3px solid white;
}
.checked::before {
  /* background: black; */
  color: white;
  opacity: 0.5;
}
.checked {
  /* background: linear-gradient(45deg, #333333, #626b73);
box-shadow: 0px 4px 30px rgba(63, 65, 67, 0.6); */
  background: linear-gradient(45deg, #d5135a, #f05924);
  box-shadow: 0px 4px 30px rgba(223, 45, 70, 0.35);
}
</style>