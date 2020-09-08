<template>
    <div id = "mainPageButton">
        <button type = "button" v-on:click = "MainPageButton">FinPic</button>
    </div>
</template>

<script>
export default {
    name : "MainPageButton",
    methods : {
        MainPageButton : function(){
            this.$router.push("/")
            this.$router.go("/")
        }
    },
}
</script>