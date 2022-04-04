<template>
  <div>
    <h1>나의 여행 목록</h1>
    <!-- <navi-bar></navi-bar> -->
    <!-- <plan-title></plan-title> -->
    <!-- <font-awesome-icon icon="fa-solid fa-user" /> -->
    <!-- <short-cut></short-cut> -->
    <!-- <plan-detail></plan-detail>
    <plan-plus></plan-plus> -->
    <plan-list></plan-list>
  </div>
</template>

<script>
// import components here
// import NaviBar from "@/components/common/NaviBar.vue";
// import PlanDetail from "@/components/plan/PlanDetail.vue";
// import PlanPlus from "@/components/plan/PlanPlus.vue";
// import PlanTitle from "@/components/common/PageTitle.vue";
import PlanList from "@/components/list/PlanList.vue";
// import ShortCut from "@/components/common/ShortCut.vue";

export default {
  name: "ListView",
  components: {
    PlanList,
    // ShortCut,
    // NaviBar,
    // PlanDetail,
    // PlanPlus,
    // PlanTitle,
  },
};
</script>

<style scoped>
h1 {
  color: #000000;
  font-weight: bolder;
  margin: 2.5rem 0 1.5rem;
  text-align: center;
  margin: 20px;
  padding: 10px;
  font-size: 45px;
}
/* .v12_109 {
  width: 100%;
  height: 100%;
  background-image: url(../assets/back.png);
  opacity: 1;
  position: absolute;
  top: 0px;
  left: 0px;
  overflow: hidden;
} */
/* body {
  background-image: url(../assets/back.png);
} */
</style>
