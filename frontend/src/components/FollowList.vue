<template>
        <div class="mypageimgContainer">
      <div class="wrapper">
        <div class="mypageimg" id="show" v-for="follow in followList" v-bind:key="follow.followId">
          <button
            class="userBtn"
            type="button"
            v-on:click="followPage(follow.followEmail, follow.followNumber)"
            v-if="toggleFollowList"
          >
            <img class="pageImg" v-bind:src="follow.followImg" />
            {{follow.followName}} {{follow.followEmail}} {{follow.followNumber}}
          </button>
        </div>
      </div>
</template>
<script>
export default {
    
}
</script>
<style scoped>

</style>