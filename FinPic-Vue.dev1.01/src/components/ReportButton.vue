<template>
    <div id = "reportButton">
        <button type = "button" v-on:click = "report()" v-if="toggle && token != null && token.length != 0 && token != 'undefined'">report</button>
        <button type = "button" v-on:click = "report()" v-if="!toggle && token != null && token.length != 0 && token != 'undefined'">disreport</button>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "ReportButton",
    data : function(){
        return {
            toggle : true,
            pictureNumber : "",
            token : storage.getItem("jwt-auth-token"),
        }
    },
    methods: {
        report : function(){
            let self = this;
            this.$axios.put("http://localhost:80/report/"+storage.getItem("userNumber")+"/"+this.pictureNumber)
            .then(res => {
                console.log(res);
                self.toggle = !self.toggle;
            })
        }
    },
}
</script>