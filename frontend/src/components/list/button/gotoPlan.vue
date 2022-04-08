<template>
  <!-- <div>계획<br />보러가기</div> -->
  <v-btn
    @click="gotoPlan"
    style="
      border-radius: 20px;
      font-weight: bold;
      font-size: large;
      padding: 40px;
      margin-right: 4px;
    "
    class="small-erea"
    text
  >
    계획<br />보러가기
  </v-btn>
</template>

<script>
export default {
  name: "gotoPlan",
  methods: {
    gotoPlan: function () {
      console.log("계획 보러가기 버튼을 눌렀습니다");
      this.$router.push({ path: "/plan" });
    },
    gotoReview: function () {
      console.log("방문기 보러가기 버튼을 눌렀습니다");
      this.$router.push({ path: "/review" });
    },
  },
};
</script>

<style>
.small-erea {
  background: rgb(255, 202, 67);
  /* color: black; */
  border-radius: 20px;
  margin-top: 5px;
  font-weight: bold;
  font-size: medium;
}
</style>
