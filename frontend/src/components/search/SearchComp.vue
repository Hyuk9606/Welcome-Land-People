<template>
  <div class="search" data-app>
    <sheader-comp></sheader-comp>
    <slist-comp></slist-comp>
  </div>
</template>

<script>
import SheaderComp from "../search/SheaderComp.vue";
import SlistComp from "../search/SlistComp.vue";

export default {
  components: {
    SheaderComp,
    SlistComp,
  },
};
</script>

<style scoped>
.search {
  background: #d8e8f2;
  border-radius: 20px;
  width: 70%;
  height: 100%;
  margin-right: 15px;
}
</style>
