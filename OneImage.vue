<template>
    <div id = "image_show_box">
        <button type = "button" v-on:click = "req()">getPictures</button>
        <div id = "show">
            <img v-bind:src="imgSrc">
        </div>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "OneImage",
    data : function(){
        return {
            imgSrc : "",
        }
    },
    methods: {
        req : function(){
            //let self = this
            this.$axios.get("http://127.0.0.1:80/pictures/"+storage.getItem("userNumber"))
            .then(res => {
                console.log(res.data);
            })
        }
    },
}
</script>