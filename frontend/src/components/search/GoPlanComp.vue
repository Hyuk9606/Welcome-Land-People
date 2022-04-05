<template>
  <div class="go" @click="test">
    <span class="goBtn">계획 Go</span>
    <img src="@/assets/images/character2.png" />
  </div>
</template>

<script>
export default {
  methods: {
    test() {
      console.log("계획go!");
    },
  },
};
</script>

<style scoped>
.go {
  background: #d8e8f2;
  width: 28%;
  height: 8%;
  border-radius: 20px;
  margin-top: 8px;
  margin-bottom: 15px;
  text-align: center;
  position: relative;
}
.goBtn {
  position: relative;
  font-size: 25px;
  font-weight: bold;
  top: 10%;
  vertical-align: middle;
}
.go img {
  position: absolute;
  width: 16%;
  height: 80%;
  bottom: 5px;
}
</style>
