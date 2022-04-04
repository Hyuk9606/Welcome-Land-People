<template>
  <div>
    <!-- 리뷰할 여행 정보 -->
    <!-- grid system 을 구성하는 가장 큰 틀로 요소들을 중앙정렬로 배치하는 tag, fluid 를 사용해서 viewport 를 100% 사용 -->
    <v-container fluid>
      <!-- v-col 을 감싸주는 tag, dense 를 추가하여 gutter 24px 폭?을 줄일 수 있다 -->
      <v-row>
        <!-- grid system 의 최하단 tag, 12points 에 대한 적절한 배치를 수행, breakpoints 기준 -->
        <v-col cols="12">
          <!-- div 말고 v-card 로 tag 이름을 설정하면 그림자가 생긴다 -->
          <div class="vuetify_grid_css_1_info">
            <trip-name></trip-name>
          </div>
          <div class="vuetify_grid_css_2_info">
            <trip-schedule></trip-schedule>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import TripName from "@/components/review/trip/TripName.vue";
import TripSchedule from "@/components/review/trip/TripSchedule.vue";

export default {
  name: "TripInfomation",
  components: {
    TripName,
    TripSchedule,
  },
};
</script>

<style scoped>
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
.info-back-size {
  width: 100%;
  height: 100%;
}
.vuetify_grid_css_1_info {
  /** 여기부터 배경 및 둘레 */
  background: rgba(255, 254, 252, 0.800000011920929);
  opacity: 1;
  border-radius: 20px;
  /** 여기부터 여백 및 포지션 */
  height: 4rem;
  /* width: 80%; */
  /* top: 5%; */
  /* left: 7%; */
  margin: 0px; /** 영역 겉쪽 여유 공간 */
  padding: 6px; /** 영역 안쪽 여유 공간 */
  /** 여기부터 스크롤바 */
  overflow: hidden;
}
.vuetify_grid_css_2_info {
  /** 여기부터 배경 및 둘레 */
  background: rgba(255, 254, 252, 0.800000011920929);
  opacity: 1;
  border-radius: 20px;
  /** 여기부터 여백 및 포지션 */
  height: 80vh;
  /* width: 80%; */
  /* top: 5%; */
  /* left: 7%; */
  margin-top: 20px; /** 영역 겉쪽 여유 공간 */
  margin-left: 0px;
  margin-right: 0px;
  margin-bottom: 10px;
  padding: 20px; /** 영역 안쪽 여유 공간 */
  /** 여기부터 스크롤바 */
  overflow: hidden;
  overflow-y: scroll;
}
</style>
