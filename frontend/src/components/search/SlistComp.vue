<template>
  <div id="contents">
    <v-container fluid class="search">
      <v-row v-for="(content, i) in contents" :key="i" class="searchPlace">
        <v-col class="d-flex" cols="12" sm="12">
          <div class="placeImg"><img :src="content.thumbnailPath" /></div>
          <div class="place">
            <div class="placeName">{{ content.title }}</div>
            <div class="placeAddress">
              {{ content.roadAddress }}
            </div>
          </div>
          <div class="basket">
            <div
              :id="content.contentsId"
              class="basketBtn"
              @click="inputPlace(content.contentsId)"
            >
              <div v-if="isInclude(content.contentsId)">
                <i class="fa-xl fa-solid fa-cart-shopping"></i>
              </div>
              <div v-else>
                <i class="fa-xl fa-solid fa-cart-plus"></i>
              </div>
            </div>
          </div>
        </v-col>
      </v-row>

      <!-- <div class="text-center">
        <v-pagination v-model="page" :length="4" circle></v-pagination>
      </div> -->
    </v-container>
  </div>
</template>

<script>
import accountApi from "@/api/account";
import { mapGetters, mapMutations } from "vuex";

const account = "account";

export default {
  props: ["contents", "page"],
  data() {
    return {};
  },
  methods: {
    ...mapMutations(account, ["pushUserContents", "popUserContents"]),

    inputPlace(contentsId) {
      if (!this.isInclude(contentsId)) {
        accountApi.likeContent(contentsId, () => {
          this.pushUserContents(contentsId);
        });
      } else {
        accountApi.dislikeContent(contentsId, () => {
          this.popUserContents(contentsId);
        });
      }
    },
    isInclude(contentsId) {
      return this.user.userContents.find((el) => {
        if (el.trim() == contentsId.trim()) return true;
      });
    },
  },
  computed: {
    ...mapGetters(account, ["user"]),
  },
};
</script>

<style scoped>
#contents {
  height: 60vh;
  overflow-y: scroll;
}
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
  font-size: 12px;
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
.fa-cart-shopping {
  color: #b9b7b7;
}
</style>
