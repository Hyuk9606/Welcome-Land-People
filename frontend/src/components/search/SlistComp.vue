<template>
  <div>
    <v-container fluid class="search">
      <v-row class="searchPlace">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div class="basketBtn" @click="inputPlace">
              <i class="fa-xl fa-solid fa-cart-shopping"></i>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="searchPlace">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div class="basketBtn" @click="inputPlace">
              <i class="fa-xl fa-solid fa-cart-shopping"></i>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="searchPlace">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div class="basketBtn" @click="inputPlace">
              <i class="fa-xl fa-solid fa-cart-shopping"></i>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="searchPlace">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div class="basketBtn" @click="inputPlace">
              <i class="fa-xl fa-solid fa-cart-shopping"></i>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row class="searchPlace">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="placeImg"><img src="@/assets/images/place1.png" /></div>
          <div class="place">
            <div class="placeName">박중섭 미술관</div>
            <div class="placeAddress">제주특별자치도 서귀포시 이중섭로 27-3-444</div>
          </div>
          <div class="basket">
            <div class="basketBtn" @click="inputPlace">
              <i class="fa-xl fa-solid fa-cart-shopping"></i>
            </div>
          </div>
        </v-col>
      </v-row>
      <div class="text-center">
        <v-pagination v-model="page" :length="4" circle></v-pagination>
      </div>
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
  methods: {
    inputPlace() {
      console.log("장바구니에 넣기");
    },
  },
};
</script>

<style scoped>
.d-flex {
  padding: 10px;
  height: 18%;
}
.container .search {
  height: 100%;
}

.search {
  width: 100%;
  height: 100%;
}
.searchPlace {
  margin: 3px;
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
  margin: 5px auto;
}
.basketBtn {
  float: left;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  background: #ffffff;
  text-align: center;
  top: 10px;
}
.basketBtn i {
  margin-top: 23px;
  margin-right: 2px;
}
</style>
