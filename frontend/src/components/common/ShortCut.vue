<template>
  <div class="parent_div">
    <div class="child_div_1">
      <router-link to="/">메인페이지로 </router-link>
    </div>
    <div class="child_div_2">
      <router-link to="/">로그인</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShortCut",
};
</script>

<style scoped>
.parent_div {
  font-size: small;
  position: fixed;
  top: 1em;
  right: 1em;
  width: 130px;
  height: 10px;
  float: left;
}
.child_div_1 {
  float: left;
  margin-right: 5px;
}
</style>
