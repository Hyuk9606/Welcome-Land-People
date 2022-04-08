<template>
  <div>
    <!-- 방문기 조회 컴포넌트 -->
    <v-container fluid>
      <!-- v-col 을 감싸주는 tag, dense 를 추가하여 gutter 24px 폭?을 줄일 수 있다 -->
      <v-row>
        <!-- grid system 의 최하단 tag, 12points 에 대한 적절한 배치를 수행, breakpoints 기준 -->
        <v-col cols="12">
          <div class="image_box">
            이곳에 이미지
            <v-carousel
              show-arrows
              height="300"
              hide-delimiter-background
              show-arrows-on-hover
            >
              <v-carousel-item
                v-for="(item, i) in items"
                :key="i"
                :src="item.src"
                reverse-transition="fade-transition"
                transition="fade-transition"
              ></v-carousel-item>
            </v-carousel>
          </div>
          <div class="text_box">{{ this.review.text }}</div>
          <div class="button_position">
            <v-btn
              @click="gotoUpdate"
              rounded
              class="button_style"
              style="background: rgb(111, 117, 121)"
              >방문기 수정</v-btn
            >
            <v-btn
              @click="gotoDelete"
              rounded
              class="button_style"
              style="background: rgb(111, 117, 121)"
              >방문기 삭제</v-btn
            >
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import http from "@/util/http-common.js";
import reviewApi from "@/api/review";

export default {
  name: "ReviewRead",
  components: {
    //
  },
  props: ["review"],
  data() {
    return {
      // items: [
      //   {
      //     src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
      //   },
      //   {
      //     src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
      //   },
      //   {
      //     src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
      //   },
      //   {
      //     src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
      //   },
      // ],
    };
  },
  created() {
    reviewApi.getReview(this.$route.query.travelSeq, (res) => {
      this.review = res;
    });
  },
  methods: {
    gotoUpdate: function () {
      console.log("방문기 수정 버튼을 눌렀습니다");
      const param = { review: this.review };
      this.$router.push({
        // path: "/review/update",
        name: "ReviewUpdate",
        params: {
          data: this.review,
          travelSeq: this.$route.query.travelSeq,
        },
      });
    },
    gotoDelete: function () {
      console.log("방문기 삭제 버튼을 눌렀습니다");
      this.$router.push({ path: "/review/delete" });
    },
    imageFormat(img) {
      return "data:image/png;base64," + img;
    },
  },
};
</script>

<style scoped>
.v-window__next {
  float: right;
  background: rgba(0, 0, 0, 0.15);
  height: 68px;
  width: 68px;
  top: calc(50% - 33px);
  margin-right: 13%;
  display: none;
}
.v-window__prev {
  float: left;
  background: rgba(0, 0, 0, 0.15);
  height: 68px;
  width: 68px;
  top: calc(50% - 33px);
  margin-left: 13%;
  display: none;
}
/** 방문기 수정, 방문기 삭제 버튼 css */
.button_style {
  color: white;
  font-weight: bold;
  background: rgb(230, 239, 247);
  margin: 10px; /* 영역 겉쪽 여유 공간 .. 이건 먹음 */
  padding: 10px; /** 영역 안쪽 여유 공간 .. 이거 안먹음 */
}
.button_position {
  /** 진짜 이거 혁명적이다 float... 어떻게 배치해야 될지 오만 고민 다했는데 ㅠㅠㅠ */
  float: right;
}
/** 이미지, 텍스트 박스 css */
.image_box {
  /** 배경 및 둘레 */
  /* background: rgba(255, 254, 252, 0.800000011920929); */
  background: rgb(207, 226, 243);
  opacity: 1;
  border-radius: 20px;
  /** 여백 및 포지션 */
  /* height: 160%; */
  /* width: 80%; */
  /* top: 5%; */
  /* left: 7%; */
  margin: 10px;
  padding: 20px;
  /** 스크롤바 */
  overflow: hidden;
  overflow-y: scroll;
}
.text_box {
  /** 배경 및 둘레 */
  /* background: rgba(255, 254, 252, 0.800000011920929); */
  background: rgb(207, 226, 243);
  opacity: 1;
  border-radius: 20px;
  /** 여백 및 포지션 */
  height: 33%;
  /* width: 80%; */
  /* top: 5%; */
  /* left: 7%; */
  margin-top: 10px;
  margin-right: 10px;
  margin-left: 10px;
  margin-bottom: 7px;
  padding: 20px;
  /** 스크롤바 */
  overflow: hidden;
  overflow-y: scroll;
}
/** 스크롤 바 없는 스크롤 기능 css*/
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
</style>
