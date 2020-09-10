<template>
  <div id="ShowUserEmail">{{ userEmail }}</div>
</template>
<script>
const storage = window.sessionStorage;

export default {
  name: "ShowUserEmail",
  data() {
    return {
      userEmail: storage.getItem("userEmail"),
    };
  },
};
</script>