<template>
    <div id = "favoriteButton">
        <button type = "button" v-bind:class="checked(add)" v-if="token != null && token.length != 0 && token != 'undefined'" v-on:click = "favoriteAdd(add)">Favorites</button>
    </div>
</template>
<script>
// import EventBus from '../EventBus/EventBus'
const storage = window.sessionStorage;

export default {
    name : "FavoriteButton",
    props:['mypictureNumber'],
    data : function(){
        return {
            add : "",
            token : storage.getItem("jwt-auth-token"),
        }
    },
    created:function(){
        let self = this;
        this.$axios.get("http://127.0.0.1:80/favorite/"+storage.getItem("userNumber")+"/check/"+ self.mypictureNumber)
        .then(res => {
            console.log(res.data);
            self.add=res.data;
        });
    },
    // beforeUpdate:function() {
    //      EventBus.$on('favoriteChange',x=>{
    //         if(x!=null){
    //             this.add=x;//false;
    //         }
    //     });     
    // },
    methods : {
        checked: function(add){
            if(add){
                return {checked: true }; //추가->버튼 색깔 바꾸기.
            }else{
                return {checked: false };//추가취소->버튼 색깔 바꾸기
            }
        },
        favoriteAdd : function(add){
            if(add){//true이면 삭제
                let self = this;
                this.$axios({
                method: "delete",
                url: "http://127.0.0.1:80/favorite/"+ storage.getItem("userNumber")+"/"+ self.mypictureNumber
                })
                .then(res => {
                    self.add =res.data;
                });
            }else{//false이면 추가
                let self = this;
                this.$axios.post("http://127.0.0.1:80/favorite/"+storage.getItem("userNumber")+"/"+ self.mypictureNumber)
                .then(res => {
                    self.add = res.data; 
                });
            }
        },
        
    },
}
</script>
<style scoped>
.checked::before{
background-color: #e7e7e7; color: black;
}
.checked{
background-color: #f44336;    
color:#e7e7e7;
}
</style>