<template>
  <div class="container">
    <search-comp></search-comp>
    <map-comp></map-comp>
    <basket-comp></basket-comp>
    <go-plan-comp></go-plan-comp>
  </div>
</template>

<script>
import SearchComp from "../components/search/SearchComp.vue";
import BasketComp from "../components/search/BasketComp.vue";
import MapComp from "../components/search/MapComp.vue";
import GoPlanComp from "../components/search/GoPlanComp.vue";

export default {
  components: {
    SearchComp,
    BasketComp,
    MapComp,
    GoPlanComp,
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
