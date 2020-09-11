<template>
  <div id="ShowPost">
    <div id="show">
      <img v-bind:src="post.img" />
      <LikeButton />
      <ReportButton />
    </div>
  </div>
</template>
<script>
const storage = window.sessionStorage;

import LikeButton from "./LikeButton.vue";
import ReportButton from "./ReportButton.vue";
export default {
  name: "ShowPost",
  data: function () {
    return {
      post: "",
    };
  },
  components: {
    LikeButton,
    ReportButton,
  },
  created() {
    let self = this;
    this.$axios
      .get("http://127.0.0.1:80/post/" + storage.getItem("pictrueNumber"))
      .then((res) => {
        self.post.push({
          pictureNumber: res.data.pictureNumberList[0],
          userEmail: res.data.pictureObject[0].userId.userEmail,
          userNumber: res.data.pictureObject[0].userId.userNumber,
          img: res.data.img[0],
        });
      });
    console.log(storage.getItem("pictrueNumber"));
    console.log(storage.getItem("otherUserEmail"));
    console.log(storage.getItem("otherUserNumber"));
  },
};
</script>