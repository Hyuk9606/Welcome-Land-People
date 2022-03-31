<template>
  <div class="startContainer" style="height: 300px">
    <span class="startBtn">
      내 여행
      <img width="50px" height="50px" src="@/assets/images/character1.png" />
    </span>
  </div>
</template>

<script>
// import {} from ;
export default {
  components: {},
};
</script>

<style scoped>
.startContainer {
  float: left;
  width: 30%;
  display: table-cell;
  vertical-align: middle;
}
.startBtn {
  position: relative;
  top: 50%;
  width: 19rem;
  height: 80px;
  line-height: 80px;
  background-color: #41ac0a;
  border-radius: 5px;
  margin: 0 auto;
  text-align: center;
  font-size: 50px;
  font-weight: bold;
  color: white;
  /* 추가 */
  display: block;
}
</style>
