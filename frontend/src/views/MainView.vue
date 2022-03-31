<template>
  <div class="container">
    <jeju-comp></jeju-comp>
    <start-comp></start-comp>
    <share-comp></share-comp>
  </div>
</template>

<script>
import JejuComp from "../components/main/JejuComp.vue";
import StartComp from "../components/main/StartComp.vue";
import ShareComp from "../components/main/ShareComp.vue";

export default {
  components: {
    JejuComp,
    StartComp,
    ShareComp,
  },
};
</script>

<style scoped>
.container {
  width: 100%;
}
</style>
