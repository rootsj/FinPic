<template>
    <div id = "followListButton">
        <button type = "button" v-on:click = "followSearch" v-if="toUserEmail==userEmail">
            FollowList({{followCount}})
        </button>
        <div id = "show" v-for="follow in followList" v-bind:key = "follow.followId">
            <button type = "button" v-on:click = "mypage(follow.userEmail, follow.followNumber)">
                {{follow.followName}} {{follow.followEmail}} {{follow.followNumber}}
            </button>
        </div>

	</div>
</template>

<script>
    const storage = window.sessionStorage;

    export default {
        name: "followListButton",
        data: function () {
            return {
                userNumber: storage.getItem("userNumber"),
                userEmail: storage.getItem("userEmail"),
                toUserEmail: storage.getItem("otherUserEmail"),
                followList: [],
                followCount: "",
            };
        },
        methods: {
            followSearch: function () {
                let self = this;
                this.$axios
                    .get("http://localhost:80/follow/follow/"+self.userNumber)
                    .then((res) => {
                        self.followList = [];
                            for(let i = 0 ; i < res.data.length;i++){
                                self.followList.push({
                                followId : i,
                                followName: res.data[i].toUser.userName,
                                followNumber: res.data[i].toUser.userNumber,
                                followEmail: res.data[i].toUser.userEmail,
                                })
                            }
                    })
                    .catch();
            },
            mypage : function(y,z){
                storage.setItem("otherUserEmail", y);
                storage.setItem("otherUserNumber", z);
                this.$router.push("/mypage");
                this.$router.go("/");
            },
        },
        mounted() {
            let self = this;
            this.$axios
                .get("http://127.0.0.1:80/follow/count/" + self.userNumber)
                .then(res => {
                    console.log(res.data);
                    self.followCount = res.data;
                })
        .catch();
        },
    };
</script>