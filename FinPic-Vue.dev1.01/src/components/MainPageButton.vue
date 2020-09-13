<template>
<<<<<<< HEAD
    <div id = "mainPageButton">
        <button type = "button" v-on:click = "MainPageButton">FinPic</button>
    </div>
=======
  <div id="mainPageButton">
    <a href="/mainpage">
      <img src="./../assets/Logo.png" alt="FinPic" width="300" />
    </a>
  </div>
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
</template>

<script>
export default {
<<<<<<< HEAD
    name : "MainPageButton",
    methods : {
        MainPageButton : function(){
            this.$router.push("/mainpage");
            this.$router.go("/");
        }
    },
}
=======
  name: "MainPageButton",
};
>>>>>>> 138afde70951ec04c39d0e74e8704962af1a7828
</script>