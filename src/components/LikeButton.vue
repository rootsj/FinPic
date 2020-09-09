<template>
    <div id = "likeButton">
        <button type = "button" v-on:click = "like()" v-if="toggle">like</button>
        <button type = "button" v-on:click = "like()" v-if="!toggle">dislike</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "LikeButton",
    data : function(){
        return {
            toggle : true,
            pictureNumber : "",
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
</script>