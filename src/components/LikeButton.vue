<template>
    <div id = "likeButton">
        <button type = "button" v-on:click = "like()">like</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "LikeButton",
    props : [
        pictureNumber,
    ],
    methods: {
        like : function(){
            this.$axios.put("http://localhost:80/users/like/"+storage.getItem("userNumber")+"/"+this.pictureNumber)
            .then(res => {
                console.log(res);
            })
        }
    },
}
</script>