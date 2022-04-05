import Vue from "vue";
import VueRouter from "vue-router";

import MainView from "../views/MainView.vue";
import SearchView from "../views/SearchView.vue";

import JimView from "@/views/JimView.vue";
import PlanView from "@/views/PlanView.vue";

// 여행 목록 페이지
import ListView from "@/views/ListView.vue";

// 여행기 작성 페이지
import ReviewView from "@/views/ReviewView.vue";
import ReviewBegin from "../components/review/ReviewBegin.vue";
import ReviewCreate from "../components/review/ReviewCreate.vue";
import ReviewDelete from "../components/review/ReviewDelete.vue";
import ReviewRead from "../components/review/ReviewRead.vue";
import ReviewUpdate from "../components/review/ReviewUpdate.vue";
import OauthRedirect from "@/components/oauth/Redirect";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: MainView,
    children: [
      {
        path: "/oauth/redirect",
        name: "OauthRedrect",
        component: OauthRedirect,
      },
    ],
  },
  {
    path: "/search",
    component: SearchView,
  },
  {
    path: "/jim",
    name: "Jim",
    component: JimView,
  },
  {
    path: "/list",
    name: "List",
    component: ListView,
  },
  {
    path: "/plan",
    name: "Plan",
    component: PlanView,
  },
  {
    // path, redirect 주석은 프론트 테스트 까지는 적용하지 않고
    // 백앤드 연결 후 API 테스트 할 때 풀어서 적용할 예정
    // path: "/review/:tripno",
    path: "/review",
    name: "Review",
    component: ReviewView,
    redirect: "/review/read",
    children: [
      {
        path: "begin",
        name: "ReviewBegin",
        component: ReviewBegin,
      },
      {
        path: "create",
        name: "ReviewCreate",
        component: ReviewCreate,
      },
      {
        path: "delete",
        name: "ReviewDelete",
        component: ReviewDelete,
      },
      {
        path: "read",
        name: "ReviewRead",
        component: ReviewRead,
      },
      {
        path: "update",
        name: "ReviewUpdate",
        component: ReviewUpdate,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
