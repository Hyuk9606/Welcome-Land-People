<template>
  <div class="chips-box">
    <div>
      <!-- <span class="material-icons" style="float: left"> arrow_circle_left </span> -->
      <h2 class="text-center">◀ Day 1 ▶</h2>
      <!-- <span class="material-icons" style="float: right"> arrow_circle_right </span> -->
    </div>
    <v-btn
      v-if="!chip1 && !chip2 && !chip3 && !chip4"
      close
      color="primary chip_st"
      dark
      @click="(chip1 = true), (chip2 = true), (chip3 = true), (chip4 = true)"
    >
      태그 생성하기
    </v-btn>

    <v-chip
      v-if="chip1"
      class="ma-2 chip_st"
      style="border: solid 3px"
      color="red"
      large
      outlined
    >
      육층집 펜션
    </v-chip>

    <v-chip
      v-if="chip2"
      class="ma-2 chip_st"
      style="border: solid 3px"
      large
      color="primary"
      outlined
      @click:close="chip2 = false"
    >
      이중섭 미술관
    </v-chip>

    <v-chip
      v-if="chip3"
      class="ma-2 chip_st"
      style="border: solid 3px"
      large
      color="green"
      outlined
      @click:close="chip3 = false"
    >
      오설록 카페
    </v-chip>

    <v-chip
      v-if="chip4"
      class="ma-2 chip_st"
      style="border: solid 3px"
      large
      color="orange"
      outlined
      @click:close="chip4 = false"
    >
      이호테우 바다
    </v-chip>

    <v-chip
      v-if="chip4"
      class="ma-2 chip_st"
      style="border: solid 3px"
      large
      color="green"
      outlined
      @click:close="chip4 = false"
    >
      랜디스 도넛
    </v-chip>

    <v-chip
      v-if="chip4"
      class="ma-2 chip_st"
      style="border: solid 3px"
      large
      color="green"
      outlined
      @click:close="chip4 = false"
    >
      울트라마린
    </v-chip>
  </div>
</template>

<script>
export default {
  name: "TripSchedule",
  data() {
    return {
      chip1: true,
      chip2: true,
      chip3: true,
      chip4: true,
      chip5: true,
    };
  },
};
</script>

<style>
.chip_st {
  margin: 10px;
  /* min-width: 17rem; */
  font-weight: bold;
  display: block;
  width: 95%;
  height: auto;
}
</style>
