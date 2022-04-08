<template>
  <div class="basket">
    <div class="basketHeader"><h1>찜목록</h1></div>
    <div class="basketList">
      <span v-for="(item, i) in contents" :key="i">
        <v-chip class="ma-2" :color="getLabel(item.label)" text-color="white">
          {{ item.title }}
        </v-chip>
      </span>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import accountApi from "@/api/account";
const account = "account";
export default {
  data() {
    return {
      chip3: true,
      contents: [],
    };
  },
  created() {
    // console.log(this.contents);
    accountApi.getLikeContents((res) => {
      this.contents = res;
    });
  },
  methods: {
    ...mapMutations(account, ["popUserContents"]),
    getLabel(label) {
      if (label == "숙박") return "red";
      else if (label == "음식점") return "green";
      else if (label == "관광지") return "orange";
      else if (label == "쇼핑") return "deep-purple darken-1";
      else return "blue";
    },
    dislike(contentsId) {
      accountApi.dislikeContent(contentsId, () => {
        this.popUserContents(contentsId);
      });
      this.contents = this.contents.filter((el) => el.contentsId != contentsId);
      this.popUserContents(contentsId);
    },
  },
  computed: {
    ...mapGetters(account, ["user"]),
    getUserContents: function () {
      return this.$store.getters["account/user"].userContents;
    },
  },
  watch: {
    getUserContents() {
      accountApi.getLikeContents((res) => {
        this.contents = res;
      });
    },
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
  overflow-y: auto;
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
.basketList .v-chip {
  margin: 1px;
}
.v-application .ma-2 {
  margin: 3px !important;
}
</style>
