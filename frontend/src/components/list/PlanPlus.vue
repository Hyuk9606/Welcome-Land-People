<template>
  <v-card
    @click="addPlan"
    class="round-box mx-auto"
    max-width="400"
    min-height="170"
    height="90%"
    style="background: rgb(142, 207, 255); border-top-left-radius: 20px; border-top-right-radius: 20px; border-bottom-left-radius: 20px; border-bottom-right-radius: 20px"
  >
    <v-card-title class="center text-style"> + </v-card-title>
  </v-card>
</template>

<script>
export default {
  name: "PlanPlus",
  methods: {
    addPlan: function () {
      console.log("여행 추가 버튼을 눌렀습니다");
      this.$router.push({ path: "/plan" });
    },
  },
};
</script>

<style scoped>
.center {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.text-style {
  font-weight: bold;
  font-size: xX-large;
}
</style>
