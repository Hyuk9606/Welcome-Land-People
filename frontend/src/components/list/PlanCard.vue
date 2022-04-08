<!-- 카드에 CSS 입힌 후 -->
<template>
  <!-- vuetify gird 시스템 이해하기 
  레퍼런스 1 : https://chansnotes.github.io/2019/07/27/vuetify-grid-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/
  레퍼런스 2 : https://ayoteralab.tistory.com/entry/Vuejs-10-use-v-form -->
  <v-container fluid>
    <v-layout row wrap>
      <v-row dense>
        <v-col
          cols="12"
          md="4"
          sm="6"
          v-for="(detail, index) in details"
          v-bind:key="index"
        >
          <v-card
            style="border-radius: 20px; background: rgb(142, 207, 255)"
            class="round-box mx-auto"
            max-width="400"
          >
            <v-card-subtitle
              class="pb-0"
              style="font-weight: bold; margin-top: 10px; margin-bottom: 10px"
            >
              {{ detail.startAt }} ~ {{ detail.endAt }}
            </v-card-subtitle>
            <v-card-title class="card-erea center-row text-style">
              {{ detail.travelTitle }}
            </v-card-title>
            <v-card-actions class="center-row">
              <!-- <goto-plan></goto-plan> -->
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
              <v-btn
    @click="gotoReview(detail.travelSeq)"
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
              <!-- <goto-review></goto-review> -->
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col cols="12" md="4" sm="6">
          <plan-plus></plan-plus>
        </v-col>
      </v-row>
    </v-layout>
  </v-container>
</template>
<script>
// testJSON : https://blog.naver.com/PostView.naver?blogId=jogilsang&logNo=222504849580&parentCategoryNo=69&categoryNo=&viewDate=&isShowPopularPosts=true&from=search
// import PlanPage from "@/components/plan/PlanPlus.vue";
import PlanPlus from "@/components/list/PlanPlus.vue";
import gotoPlan from "@/components/list/button/gotoPlan.vue";
import gotoReview from "@/components/list/button/gotoReview.vue";
import travelApi from "@/api/travel.js";

export default {
  name: "PlanCard",
  components: {
    PlanPlus,
    gotoPlan,
    gotoReview,
  },
  created(){
    travelApi.getTravel((res) => {
      console.log(res);
      this.details = res;
    });
  },
  methods: {
    gotoPlan: function () {
      console.log("계획 보러가기 버튼을 눌렀습니다");
      this.$router.push({ path: "/plan" });
    },
    gotoReview: function (travelSeq) {
      console.log("방문기 보러가기 버튼을 눌렀습니다", travelSeq);
      this.$router.push({ path: "/review", query: {"travelSeq":travelSeq} });
    },
  },
  data: function () {
    return {
      index: "",
      details: [],
    };
  },
};
</script>
<style scped>
.center-row {
  place-content: center;
}
.round-box {
  width: 90%;
  height: 90%;
  text-align: center;
  background: rgb(142, 207, 255);
  border-radius: 20px;
  margin: 20px;
}
.text-style {
  font-weight: bold;
  font-size: x-large;
}
.card-erea {
  background: rgb(255, 202, 67);
  border-radius: 20px;
  margin-left: 10px;
  margin-right: 10px;
  padding: 15px;
}
.small-erea {
  background: rgb(255, 202, 67);
  /* color: black; */
  border-radius: 20px;
  margin-top: 5px;
  font-weight: bold;
  font-size: medium;
}
</style>
