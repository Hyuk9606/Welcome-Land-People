<template>
  <div class="basket">
    <div class="basketHeader"><h1>찜목록</h1></div>
    <div class="basketList">
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
      <v-chip v-if="chip3" class="ma-2" close color="green" outlined @click:close="chip3 = false"> Success </v-chip>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chip3: true,
    };
  },
};
</script>

<style scoped>
.basket {
  background: #d8e8f2;
  width: 28%;
  height: 40%;
  border-radius: 20px;
  margin-top: 5px;
}
.basketHeader h1 {
  color: #000000;
  font-weight: 1000;
  font-size: 30px;
  text-align: center;
  margin-top: 5px;
  height: 20%;
}
.basketList {
  height: 80%;
  margin: auto;
}
</style>
