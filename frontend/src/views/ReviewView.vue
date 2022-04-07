<template>
  <div>
    <!-- 방문기 작성 페이지 -->
    <!-- grid system 을 구성하는 가장 큰 틀로 요소들을 중앙정렬로 배치하는 tag, fluid 를 사용해서 viewport 를 100% 사용 -->
    <v-container fluid>
      <!-- v-col 을 감싸주는 tag, dense 를 추가하여 gutter 24px 폭?을 줄일 수 있다 -->
      <v-row dense>
        <!-- grid system 의 최하단 tag, 12points 에 대한 적절한 배치를 수행, breakpoints 기준 -->
        <v-col cols="3">
          <div class="vuetify_grid_css_1">
            <trip-information></trip-information>
          </div>
        </v-col>
        <v-col cols="9">
          <div class="vuetify_grid_css_2 setting_height">
            <router-view></router-view>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import TripInformation from "@/components/review/trip/TripInformation.vue";
import reviewApi from "@/api/review";
import ReviewBegin from '@/components/review/ReviewBegin.vue';
import ReviewRead from '@/components/review/ReviewRead.vue';

export default {
  name: "ReviewView",
  data(){
    return{
      review:null
    }
  },
  components: {
    TripInformation,
    ReviewBegin,
    ReviewRead,
  },
  created(){
    // console.log(this.$route.query.travelSeq);
    reviewApi.getReview(this.$route.query.travelSeq, (res) => {
      console.log(res);
      this.review = res;
      this.$router.push({path: "/review/read", query: {"travelSeq":travelSeq}});
    })
  },
};
</script>

<style scoped>
/* .review-back-size {
  width: 100%;
  height: 100%;
} */
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
/** vuetify_grid 에 적용할 반투명 하얀색 컴포넌트 영역 css 
레퍼런스 1 : https://vuetifyjs.com/en/components/grids/#row-and-column-breakpoints 
레퍼런스 2 : https://ayoteralab.tistory.com/entry/Vuejs-10-use-v-form */
/** CSS의 7가지 단위 - rem, vh, vw, vmin, vmax, ex, ch
rem 사용 시 주의 : root em 모든 자식 요소들의 사이즈에 영향을 받는다
레퍼런스 : https://webclub.tistory.com/356 */
/** CSS 프로퍼티 값의 단위 (px, em, %)
레퍼런스 : https://poiemaweb.com/css3-units */
/** 박스 각각 margin 을 일정하게 유지하기 위해서, 왼쪽 1, 오른쪽 2 로 나누어서 적용 */
.setting_height {
  font-size: 14px;
  min-height: 91vh;
}
.vuetify_grid_css_1 {
  /** 여기부터 배경 및 둘레 */
  /* background: rgba(255, 254, 252, 0.800000011920929); */
  opacity: 1;
  border-radius: 20px;
  /** 여기부터 여백 및 포지션 */
  height: 100%;
  /* width: 80%; */
  /* top: 5%; */
  /* left: 7%; */
  margin-left: 10px; /* 영역 바깥 왼쪽 여유 공간 */
  margin-top: 15px; /* 영역 바깥 위쪽 여유 공간 */
  /* margin-bottom: 25px; 영역 바깥 아래쪽 여유 공간 */
  padding: 0px; /** 영역 안쪽 여유 공간 */
  /** 여기부터 스크롤바 */
  /* overflow: hidden; */
  /* overflow-y: scroll; */
}
.vuetify_grid_css_2 {
  /** 여기부터 배경 및 둘레 */
  background: rgba(255, 254, 252, 0.800000011920929);
  opacity: 1;
  border-radius: 20px;
  /** 여기부터 여백 및 포지션 */
  /* height: 85%; */
  /* width: 80%; */
  /* top: 5%; */
  /* left: 7%; */
  margin: 25px; /** 영역 겉쪽 여유 공간 */
  margin-bottom: 0px;
  padding: 10px; /** 영역 안쪽 여유 공간 */
  /** 여기부터 스크롤바 */
  overflow: hidden;
  overflow-y: scroll;
}
</style>
