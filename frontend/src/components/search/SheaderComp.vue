<template>
  <div>
    <header>
      <h1>
        어드레 감디?
        <img width="50px" height="60px" src="@/assets/images/orange.png" />
      </h1>
      <h3>어디로 갈지 찜목록에 담아보자!</h3>
    </header>
    <div>
      <v-container fluid>
        <v-row>
          <v-col class="d-flex select" cols="12" sm="3">
            <v-select :items="items" label="읍/면/동" dense solo></v-select>
          </v-col>
          <v-col class="d-flex inputDiv" cols="12" sm="9">
            <input type="search" class="searchInput" />
            <i class="fa-2x fa-solid fa-magnifying-glass"></i>
          </v-col>
        </v-row>
        <v-card>
          <v-btn color="orange" text> 숙소 </v-btn>
          <v-btn color="orange" text> 맛집 </v-btn>
          <v-btn color="orange" text> 전시 </v-btn>
          <v-btn color="orange" text> 바다 </v-btn>
        </v-card>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: ["구좌", "남원", "대정", "서귀포", "성산", "안덕", "우도", "애월", "조천", "중문", "표선", "한경", "한림"],
    };
  },
};
</script>

<style lang="scss" scoped>
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
.searchInput {
  width: 100%;
  height: 50px;
  background: #ffffff;
  border: 3px solid #faaf3a;
  border-radius: 20px;
  outline: none;
  padding: 3px;
}
.inputDiv {
  position: relative;
}
.inputDiv i {
  position: absolute;
  right: 30px;
  top: 20px;
  color: gray;
}
.select {
  margin-top: 5px;
}
.v-select {
  border-radius: 20px;
}
.v-card {
  background: #ffffff;
}
.v-card .v-btn {
  font-size: 15px;
  font-weight: bold;
}
</style>
