<template>
    <div id = "image_show_box">
        <div id = "show" v-for="(img, index) in imgSrc" v-bind:key = "index">
            <img v-bind:src="img">
        </div>
    </div>
</template>

<script>
import EventBus from "../EventBus/EventBus.js"

export default {
    name : "AllImage",
    data : function(){
        return {
            imgSrc : "",
        }
    },
    methods: {
        req : function(){
            let self = this
            this.$axios.get("http://127.0.0.1:80/all-pictures")
            .then(res => {
                self.imgSrc = res.data
            })
        }
    },
    mounted() {
        EventBus.$on("search", x => this.imgSrc = x);
    },
    created() {
        let self = this
        this.$axios.get("http://127.0.0.1:80/all-pictures")
        .then(res => {
            self.imgSrc = res.data.img;
        })
    }
}
</script>