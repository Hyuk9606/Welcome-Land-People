<template>
  <div class="startContainer" style="height: 300px">
    <span @click="goSearch" class="startBtn">
      내 여행
      <img width="50px" height="50px" src="@/assets/images/character1.png" />
    </span>
  </div>
</template>

<script>
// import {} from ;
export default {
  components: {},
  methods: {
    goSearch() {
      this.$router.push("/search").catch(() => {});
    },
  },
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
  background-color: #41ac6c;
  border-radius: 5px;
  margin: 0 auto;
  text-align: center;
  font-size: 50px;
  font-weight: bold;
  color: white;
  display: block;
  cursor: pointer;
}
.startBtn:hover {
  background: #368f5a;
}
</style>
