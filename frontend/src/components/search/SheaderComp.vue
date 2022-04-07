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
            <v-select
              v-model="find.selected"
              :items="items"
              dense
              solo
            ></v-select>
          </v-col>
          <v-col class="d-flex inputDiv" cols="12" sm="9">
            <input
              v-model="find.query"
              type="search"
              class="searchInput"
              @keyup.enter="$emit('doSearchApi')"
            />
            <button @click="$emit('doSearchApi')">
              <i class="fa-2x fa-solid fa-magnifying-glass"></i>
            </button>
          </v-col>
        </v-row>
        <v-card>
          <v-row>
            <v-col cols="12" sm="8">
          <v-btn-toggle
            v-model="find.label"
            tile
            group
            style="btn-toggle-btn-height: 30px"
          >
            <v-btn color="red" text value="숙박"> 숙박 </v-btn>
            <v-btn color="green" text value="음식점"> 음식점 </v-btn>
            <v-btn color="deep-purple darken-1" text value="쇼핑"> 쇼핑 </v-btn>
            <v-btn color="orange" text value="관광지"> 관광지 </v-btn>
            <v-btn color="blue" text value="축제"> 축제/행사 </v-btn>
          </v-btn-toggle>
          </v-col>
            <v-col cols="12" sm="4">
            <v-carousel cycle vertical interval="2600" height="40" hide-delimiters :show-arrows="false">
              <v-carousel-item v-for="(slide, i) in slides" :key="i">  
                  <v-row id="rank" class="fill-height" align="center" justify="center" >
                    <v-chip id="rankchip" class="ma-2" small color="orange" label>{{i+1}}</v-chip>
                    <div id="ranktxt" class="text"> &nbsp; {{ slide.rank_voca }}</div>
                  </v-row>
              </v-carousel-item>
            </v-carousel>
            </v-col>
          </v-row>
        </v-card>
      </v-container>
    </div>
  </div>
  
</template>

<script>
import rankApi from "@/api/rank";
export default {
  props: ["find"],
  data() {
    return {
      // items: ["구좌", "남원", "대정", "서귀포", "성산", "안덕", "우도", "애월", "조천", "중문", "표선", "한경", "한림"],
      items: ["지역", "상호"],
      slides: [],
    };
  },
  created() {
    rankApi.getRank((res) => {
      this.slides = res;
    });
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
.v-btn-toggle:not(.v-btn-toggle--dense) .v-btn.v-btn.v-size--default {
  height: 30px;
}
.v-card .v-btn {
  font-size: 12px;
  font-weight: bold;
}
.v-carousel{
  margin: 0%;
  padding: 0%;
  height: 30px;
}
#test{
  background:#faaf3a;
   margin:0%;
  padding: 0%;
  height: 0%;

}
#rank{
  margin:0%;
  padding: 0%;
  height: 0%;
  font-weight: bold;
}

</style>
