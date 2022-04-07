<template>
  <div class="container">
    <!-- <v-container fluid> -->
    <!-- <v-layout align-center> -->
    <!-- <v-row> -->
    <!-- <v-col> -->
    <div class="guide_text">
      <div>
        아래 버튼을 눌러
        <br />
        방문기를 작성해보세요!
        <br />
        <v-btn
          x-large
          @click="gotoCreate"
          class="guide_button"
          style="
            background: rgb(196, 196, 196);
            padding-top: 30px;
            padding-bottom: 30px;
            padding-left: 60px;
            padding-right: 60px;
            margin-top: 20px;
          "
          >방문기 작성하기</v-btn
        >
      </div>
    </div>
    <!-- </v-col> -->
    <!-- </v-row> -->
    <!-- </v-layout> -->
    <!-- </v-container> -->
  </div>
</template>

<script>
export default {
  name: "ReviewBegin",
  methods: {
    gotoCreate: function () {
      console.log("방문기 작성하기 버튼을 눌렀습니다");
      console.log(this.$route.query);
      this.$router.push({path: "/review/create", query: {"travelSeq":this.$route.query.travelSeq}});
    },
  },
};
</script>

<!-- set_center flexbox ref : https://www.daleseo.com/css-centering/ -->
<!-- 이게 찐이다 set_center with flex ref : https://www.codingfactory.net/12354 -->
<style scoped>
template {
  box-sizing: border-box;
  margin: 0px;
  font-family: Consolas, monospace;
}
.container {
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.item {
  /** 원래는 아래 padding 을 추가 */
  /* padding: 20px; */
  /* position: relative;
  left: 50rem;
  top: 15rem;
  transform: translate(-50%, -50%); */
}
.set_center {
  /* display: flex;
  justify-content: center;
  align-items: center;
  min-height: 33rem; */
  position: relative; /* 부모요소에 relative */
}
.guide {
  position: absolute;
  left: 50rem;
  top: 15rem;
  /* transform: translate(-50%, -50%); */
}
.guide_text {
  font-size: 30px;
  text-align: center;
  margin: 15px;
}
.guide_button {
  /** font style */
  font-size: 20px;
  text-align: center;
  font-weight: bold;
  /** box style */
  background: rgb(196, 196, 196);
  border-radius: 20px;
  /* padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 50px;
  padding-right: 50px; */
  box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.2);
}
</style>
