import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import OauthRedirect from "@/components/oauth/Redirect";
import MainView from "../views/MainView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    children: [
      {
        path: "/oauth/redirect",
        name: "OauthRedrect",
        component: OauthRedirect,
      },
    ],
  },
  {
    path: "/",
    name: "main",
    component: MainView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
