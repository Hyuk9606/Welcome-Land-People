import Vue from "vue";
import Vuex from "vuex";
import mainView from "@/store/modules/mainView.js";

Vue.use(Vuex);

export const store = new Vuex.Store({
  modules: {
    mainView,
  },
});
