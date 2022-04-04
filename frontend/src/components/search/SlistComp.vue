<template>
  <div>
    <v-container style="height: 100%">
      <v-row class="search">
        <v-col class="d-flex searchPlace" cols="12" sm="">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div>
              <span class="basketBtn"><i class="fa-xl fa-solid fa-cart-shopping"></i></span>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="search">
        <v-col class="d-flex searchPlace" cols="12" sm="">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div>
              <span class="basketBtn"><i class="fa-xl fa-solid fa-cart-shopping"></i></span>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="search">
        <v-col class="d-flex searchPlace" cols="12" sm="">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div>
              <span class="basketBtn"><i class="fa-xl fa-solid fa-cart-shopping"></i></span>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="search">
        <v-col class="d-flex searchPlace" cols="12" sm="">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div>
              <span class="basketBtn"><i class="fa-xl fa-solid fa-cart-shopping"></i></span>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="search">
        <v-col class="d-flex searchPlace" cols="12" sm="">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div>
              <span class="basketBtn"><i class="fa-xl fa-solid fa-cart-shopping"></i></span>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-container>
        <div class="text-center">
          <v-pagination v-model="page" :length="4" circle></v-pagination>
        </div>
      </v-container>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      page: 0,
    };
  },
};
</script>

<style scoped>
.text-center {
  height: 15%;
}
.search {
  width: 100%;
  height: 15%;
}
.searchPlace {
  height: 100%;
  margin: 2px;
  border-radius: 10px;
  background-color: #ffca43;
}
.searchPlace > div {
  float: left;
}
.placeImg {
  width: 20%;
  height: 60px;
  background: #e893b0;
}
.placeImg img {
  width: 100%;
  height: 100%;
}
.place {
  width: 70%;
  height: 60px;
  padding: 5px;
}
.place > div {
  float: left;
}
.placeName {
  width: 90%;
  font-size: 18px;
  font-weight: bold;
  margin: 2px;
}
.placeAddress {
  width: 90%;
  margin: 2px;
}
.basket {
  width: 10%;
  background: #2196f3;
}
.basketBtn {
  float: left;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  background: #41ac6c;
  text-align: center;
  vertical-align: ;
}
</style>
