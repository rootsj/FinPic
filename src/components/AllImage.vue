<template>
    <div id = "image_show_box">
        <div id = "show" v-for="post in postList" v-bind:key = "post.id">
            <img v-bind:src="post.img">
            Picture number : {{post.pictureNumber}}
            User Email : {{post.userEmail}}
            <LikeButton/>
            <ReportButton/>
        </div>
    </div>
</template>

<script>
import EventBus from "../EventBus/EventBus.js"
import LikeButton from "./LikeButton.vue"
import ReportButton from "./ReportButton.vue"
export default {
    name : "AllImage",
    data : function(){
        return {
            postList : [],
        }
    },
    components : {
        LikeButton,
        ReportButton
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
        EventBus.$on("search", x => {
            this.postList = [];
            for(let i = 0 ; i < x.data.pictureNumberList.length;i++){
                    this.postList.push({
                    postId : i,
                    pictureNumber : x.data.pictureNumberList[i],
                    userEmail : x.data.pictureObject[i].userId.userEmail,
                    img : x.data.img[i],
                })
            }
        }
        );
    },
    created() {
        let self = this
        this.$axios.get("http://127.0.0.1:80/all-pictures")
        .then(res => {
            for(let i = 0 ; i < res.data.pictureNumberList.length;i++){
                self.postList.push({
                    postId : i,
                    pictureNumber : res.data.pictureNumberList[i],
                    userEmail : res.data.pictureObject[i].userId.userEmail,
                    img : res.data.img[i],
                })
            }
        })
    },

}
</script>