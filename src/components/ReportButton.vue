<template>
    <div id = "reportButton">
        <button type = "button" v-on:click = "report()" v-if="toggle">report</button>
        <button type = "button" v-on:click = "report()" v-if="!toggle">disreport</button>
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