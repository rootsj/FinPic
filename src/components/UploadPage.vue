<template>
  <div id = "uploadPage">
    <div id="picture_tags">
        <input type="text" id = "tagName" v-bind:placeholder = "tag" /><br>
    </div><br>

    <form method="post" enctype="multipart/form-data"> 
        <input type="file" enctype="multipart/form-data" v-on:change = "fileSelect()" name="photo" ref="photoimage" v-bind:style="{width : '300px', height : '300px'}"> 
    </form>
    <div id = "uploadButton">
        <button type = "button" v-on:click = "req()">File Upload</button>
    </div>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
    name : "UploadPage",
    data : function () {
        return {
            tag : "Tag Name",
            img : "",
        }
    },
    methods: {
        fileSelect : function(){
            this.img = this.$refs.photoimage.files[0];
        },
        req : function(){
            const formData = new FormData();
            formData.append("img", this.img);
            formData.append("tagName", document.getElementById("tagName").value);
            this.$axios.post("http://127.0.0.1:80/pictures-and-tags/"+storage.getItem("userNumber"),formData,{
                  headers: {
                    'Content-Type': 'multipart/form-data'
                    }
        })
        .then((res) => {console.log(res.data)
                this.$router.push("/mainpage");
                this.$router.go("/");
        })
        .catch(() => {});
        },
    },
}
</script>