<template>
  <div class="container">
    <plan-comp></plan-comp>
    <map-comp></map-comp>
    <pbasket-comp></pbasket-comp>
    <go-search-comp></go-search-comp>
  </div>
</template>

<script>
import PlanComp from "../components/plan/PlanComp.vue";
import MapComp from "../components/search/MapComp.vue";
import PbasketComp from "../components/plan/PbasketComp.vue";
import GoSearchComp from "../components/plan/GoSearchComp.vue";

export default {
  name: "PlanView",
  components: {
    PlanComp,
    MapComp,
    PbasketComp,
    GoSearchComp,
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
