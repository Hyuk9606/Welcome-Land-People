<template>
  <div class="container">
    <search-comp></search-comp>
    <map-comp></map-comp>
    <basket-comp></basket-comp>
  </div>
</template>

<script>
import SearchComp from "../components/search/SearchComp.vue";
import BasketComp from "../components/search/BasketComp.vue";
import MapComp from "../components/search/MapComp.vue";

export default {
  components: {
    SearchComp,
    BasketComp,
    MapComp,
  },
};
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
}
.container > div {
  float: left;
  padding: 10px;
}
</style>
