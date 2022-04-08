<!-- 카드에 CSS 입힌 후 -->
<template>
  <!-- vuetify gird 시스템 이해하기 
  레퍼런스 1 : https://chansnotes.github.io/2019/07/27/vuetify-grid-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/
  레퍼런스 2 : https://ayoteralab.tistory.com/entry/Vuejs-10-use-v-form -->
  <v-container fluid>
    <v-layout row wrap>
      <v-row dense>
        <v-col cols="12" md="4" sm="6" v-for="(detail, index) in details" v-bind:key="index">
          <v-card style="border-radius: 20px; background: rgb(142, 207, 255)" class="round-box mx-auto" max-width="400">
            <v-card-subtitle class="pb-0" style="font-weight: bold; margin-top: 10px; margin-bottom: 10px"> {{ details[index].start_at }} ~ {{ details[index].end_at }} </v-card-subtitle>
            <v-card-title class="card-erea center-row text-style">
              {{ details[index].traval_title }}
            </v-card-title>
            <v-card-actions class="center-row">
              <goto-plan></goto-plan>
              <goto-review></goto-review>
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
import testJSON from "@/components/list/plan_data.json";
import PlanPlus from "@/components/list/PlanPlus.vue";
import gotoPlan from "@/components/list/button/gotoPlan.vue";
import gotoReview from "@/components/list/button/gotoReview.vue";

const details = testJSON;

export default {
  name: "PlanCard",
  components: {
    PlanPlus,
    gotoPlan,
    gotoReview,
  },
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
  data: function () {
    return {
      index: "",
      details,
      // details: [],
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
