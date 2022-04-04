<template>
  <div class="box v12_112">
    <!-- <plan-detail></plan-detail> -->
    <!-- <plan-plus></plan-plus> -->
    <plan-card></plan-card>
  </div>
</template>

<script>
// import components here
// import PlanDetail from "@/components/plan/PlanDetail.vue";
// import PlanPlus from "@/components/plan/PlanPlus.vue";
import PlanCard from "@/components/list/PlanCard.vue";

export default {
  name: "PlanList",
  components: {
    // PlanDetail,
    // PlanPlus,
    PlanCard,
  },
};
</script>

<style scoped>
/** 스크롤 기능은 살리고 스크롤 바는 없애기 css
레퍼런스 : https://wooaoe.tistory.com/49 */
body {
  -ms-overflow-style: none;
}
::-webkit-scrollbar {
  display: none;
}
.box {
  -ms-overflow-style: none;
}
.box::-webkit-scrollbar {
  display: none;
}
.v12_112 {
  width: 80%;
  height: 80%;
  background: rgba(255, 254, 252, 0.800000011920929);
  opacity: 1;
  position: absolute;
  top: 10%;
  left: 8%;
  border-radius: 20px;
  /* overflow: hidden; */
  overflow-y: scroll;
  margin: 30px;
  padding: 11px;
}
</style>
