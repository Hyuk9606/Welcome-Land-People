<template>
  <div class="search" data-app>
    <sheader-comp :find="find" @doSearchApi="doSearch"></sheader-comp>
    <slist-comp class="slist" :contents="contents" :page="page"></slist-comp>
  </div>
</template>

<script>
import SheaderComp from "../search/SheaderComp.vue";
import SlistComp from "../search/SlistComp.vue";
import searchApi from "@/api/search";

export default {
  components: {
    SheaderComp,
    SlistComp,
  },
  data() {
    return {
      find: {
        selected: "지역",
        label: "숙박",
        query: "",
      },
      contents: [],
      page: 0,
    };
  },
  methods: {
    doSearch() {
      if (this.find.label == null) {
        alert("검색타입을 선택하세요");
        return;
      } else if (this.find.query == "") {
        alert("검색어를 입력하세요");
        return;
      }

      searchApi.search(this.find, (res) => {
        this.contents = res;
        this.page = res.length;
      });
    },
  },
};
</script>

<style scoped>
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
</style>
