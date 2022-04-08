<template>
  <div class="search" data-app>
    <header>
      <h1>
        어드레 감디?
        <img width="50px" height="60px" src="@/assets/images/orange.png" />
      </h1>
      <h3>어떻게 갈지 여행 코스를 짜보자!</h3>
    </header>
    <div>
      <v-container fluid>
        <v-row>
          <v-col class="d-flex title" cols="12" sm="4">
            <v-text-field label="제목"></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-menu ref="menu" v-model="menu" :close-on-content-click="false" :return-value.sync="date" transition="scale-transition" offset-y min-width="auto">
              <template v-slot:activator="{ on, attrs }">
                <v-text-field v-model="date" label="여행 시작일" prepend-icon="mdi-calendar" readonly v-bind="attrs" v-on="on"></v-text-field>
              </template>
              <v-date-picker v-model="date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false"> 취소 </v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date)"> 확인 </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
      </v-container>
      <div>
        <div class="chip">
          <h2>Day 1</h2>
          <div class="chips-box">
            <v-chip v-if="chip1" close class="ma-2 chip_st" color="red" text-color="white" @click:close="chip1 = false"> 육층집 펜션 </v-chip>
            <v-chip v-if="chip2" close class="ma-2 chip_st" color="primary" text-color="white" @click:close="chip2 = false"> 이중섭 미술관 </v-chip>
            <v-chip v-if="chip3" close class="ma-2 chip_st" color="green" text-color="white" @click:close="chip3 = false"> 오설록 카페 </v-chip>
            <v-chip v-if="chip4" close class="ma-2 chip_st" color="orange" text-color="white" @click:close="chip4 = false"> 이호테우 바다 </v-chip>
          </div>
        </div>
        <div class="chip">
          <h2>NEW</h2>
          <div class="chips-box">
            <div class="addBtn">
              <i class="fa-solid fa-plus fa-2x"></i>
            </div>
          </div>
        </div>
      </div>
      <div>
        <div @click="savePlan" class="saveBtn">
          <span class="save">저장</span>
          <!-- <img width="70px" height="70px" src="@/assets/images/character1.png" /> -->
        </div>
        <div @click="goReview" class="reviewBtn">
          <span class="save">방문기</span>
          <!-- <img width="70px" height="70px" src="@/assets/images/character1.png" /> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000).toISOString().substr(0, 10),
      menu: false,
      chip1: true,
      chip2: true,
      chip3: true,
      chip4: true,
      chip5: true,
    };
  },
  methods: {
    goReview() {
      this.$router.push("/review");
    },
    savePlan() {},
  },
};
</script>

<style scoped>
h1 {
  color: #000000;
  font-weight: 1000;
  font-size: 40px;
  text-align: center;
  margin-top: 5px;
}
h3 {
  text-align: center;
  font-size: 18px;
  margin-bottom: 5px;
}
img {
  vertical-align: middle;
  margin-bottom: 15px;
}
.slist {
  height: 70%;
}
.search {
  background: #d8e8f2;
  border-radius: 20px;
  width: 70%;
  height: 100%;
  margin-right: 15px;
}
.v-text-field {
  font-family: "MapoFlowerIsland", sans-serif !important;
}
.saveBtn {
  float: right;
  width: 8rem;
  height: 40px;
  line-height: 40px;
  background-color: #339dff;
  border-radius: 5px;
  font-size: 30px;
  font-weight: bold;
  color: white;
  cursor: pointer;
  text-align: center;
}
.reviewBtn {
  float: right;
  margin-right: 10px;
  width: 8rem;
  height: 40px;
  line-height: 40px;
  background-color: #41ac6c;
  border-radius: 5px;
  font-size: 30px;
  font-weight: bold;
  color: white;
  cursor: pointer;
  text-align: center;
}
.saveBtn img {
  position: absolute;
}
.reviewBtn:hover {
  background: #286841;
}
.saveBtn:hover {
  background: #1d5d9a;
}
.chips-box {
  width: 250px;
  height: 47vh;
  border-radius: 20px;
  background-color: #93bfe688;
  padding-top: 10px;
}
.chips-box .v-btn {
  top: 10%;
  text-align: center;
}
.chip_st {
  /* min-width: 17rem; */
  display: block;
  width: 80%;
  height: auto;
  font-weight: normal;
  font-size: 20px;
  padding: 10px;
}
.chip {
  float: left;
  margin-left: 5px;
  overflow-x: auto;
}
.addBtn {
  width: 50px;
  height: 50px;
  background-color: #3e39e4;
}
.addBtn {
  color: white;
  padding-top: 8px;
  text-align: center;
  vertical-align: middle;
  margin-left: 105px;
  margin-top: 20vh;
  border-radius: 50%;
}
</style>
