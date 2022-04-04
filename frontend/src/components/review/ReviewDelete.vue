<template>
  <div class="container">
    <div class="item">
      <!-- 방문기 삭제 컴포넌트 -->
      <!-- <br /> -->
      삭제처리중...
      <!-- <br />
      created()에 삭제 로직 작성
      <br />
      아니면 버튼누르면 바로 삭제되게끔 하고,
      <br />
      이 페이지 굳이 없어도 되는거 아닌가? -->
    </div>
  </div>
</template>

<script>
// import http from "@/util/http-common.js";

export default {
  name: "ReviewDelete",
  created() {
    // http.delete();
  },
};
</script>

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
  font-size: 30px;
  text-align: center;
  /** 원래는 아래 padding 을 추가 */
  /* padding: 20px; */
  /* position: relative;
  left: 50rem;
  top: 15rem;
  transform: translate(-50%, -50%); */
}
</style>
