<template>
  <v-app id="app">
    <!-- <tool-bar></tool-bar> -->
    <short-cut></short-cut>
    <router-view></router-view>
  </v-app>
</template>

<script>
// import ToolBar from "./components/common/ToolBar.vue";
import ShortCut from "@/components/common/ShortCut.vue";

export default {
  name: "App",
  components: {
    // ToolBar,
    ShortCut,
  },
};
</script>

<style>
#app {
  /* font-family: "Avenir", Arial, Helvetica, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-size: 20px;
  counter-reset: #2c3e50;
  margin: 20px; */

  width: 100%;
  height: 100%;
  background-image: url("@/assets/images/sea.png");
  opacity: 1;
  position: absolute;
  top: 0px;
  left: 0px;
  overflow: hidden;
  /** 여기부터 스크롤바 */
  overflow: hidden;
  overflow-y: scroll;
}
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
/* .v12_109 {
  width: 100%;
  height: 100%;
  background-image: url(C:\Users\multicampus\Desktop\GITHUB\boonmoja\frontend\src\assets\back.png);
  opacity: 1;
  position: absolute;
  top: 0px;
  left: 0px;
  overflow: hidden;
} */
</style>
