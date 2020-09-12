<template>
  <div id="DeleteID">
    <button type="button" v-on:click="DeleteAction">Delete ID</button>
  </div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "DeleteID",
  methods: {
    DeleteAction: function () {
      if (confirm("Do you want to delete your ID?") == true) {
        this.$axios
          .delete("http://localhost:80/users/" + storage.getItem("userNumber"))
          .then(() => {
            storage.setItem("jwt-auth-token", "");
            storage.setItem("userNumber", "");
            this.$router.push("/mainpage");
            this.$router.go("/");
          });
      }
    },
  },
  data: function () {
    return {
      token: storage.getItem("jwt-auth-token"),
    };
  },
};
</script>