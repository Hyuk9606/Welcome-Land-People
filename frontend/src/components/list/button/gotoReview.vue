<template>
  <!-- <div>여행기<br />보러가기</div> -->
  <v-btn
    @click="gotoReview"
    style="
      border-radius: 20px;
      font-weight: bold;
      font-size: large;
      padding: 40px;
      margin-left: 4px;
    "
    class="small-erea"
    text
  >
    방문기<br />보러가기
  </v-btn>
</template>

<script>
export default {
  name: "gotoReview",
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

<style></style>
