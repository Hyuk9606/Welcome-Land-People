import Vue from "vue";
import Vuex from "vuex";
import mainView from "@/store/modules/mainView.js";
import account from "@/store/modules/account.js";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    mainView,
    account,
  },
  plugins: [createPersistedState({ storage: sessionStorage })],
});
