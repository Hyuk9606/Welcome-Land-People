import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";
import SearchView from "../views/SearchView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: MainView,
  },
  {
    path: "/search",
    component: SearchView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
