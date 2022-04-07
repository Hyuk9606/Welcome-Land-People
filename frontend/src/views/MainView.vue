<template>
  <div>
    <header-comp
      @onOpenLoginModal="openLoginModal"
      @onOpenSignUpModal="openSignUpModal"
      @onLogout="logout"
    ></header-comp>
    <div class="container">
      <jeju-comp></jeju-comp>
      <start-comp></start-comp>
      <share-comp></share-comp>
    </div>
    <login-modal
      v-if="isLoginModalOpen"
      :isOpen="isLoginModalOpen"
      @onOpenSignUpModal="openSignUpModal"
      @onCloseModal="closeLoginModal"
    />
    <sign-up-modal
      v-if="isSignUpModalOpen"
      :isOpen="isSignUpModalOpen"
      @onCloseModal="closeSignUpModal"
    />
    <router-view />
  </div>
</template>

<script>
import HeaderComp from "@/components/HeaderComp.vue";
import JejuComp from "../components/main/JejuComp.vue";
import StartComp from "../components/main/StartComp.vue";
import ShareComp from "../components/main/ShareComp.vue";
import LoginModal from "@/components/user/LoginModal";
import SignUpModal from "@/components/user/signUpModal";
import accountApi from "../api/account";
import { mapGetters, mapMutations } from "vuex";

const account = "account";

export default {
  name: "MainView",
  components: {
    HeaderComp,
    JejuComp,
    StartComp,
    ShareComp,
    LoginModal,
    SignUpModal,
  },
  data() {
    return {
      isLoginModalOpen: false,
      isSignUpModalOpen: false,
      isScrollTop: true,
    };
  },
  created() {
    window.addEventListener("scroll", () => {
      this.isScrollTop = window.scrollY === 0;
    });
    window.onload = function () {
      console.log(process.env);
    };
  },
  methods: {
    ...mapMutations(account, ["setToken", "setUser"]),
    openLoginModal() {
      this.isLoginModalOpen = true;
    },
    openSignUpModal() {
      this.isLoginModalOpen = false;
      this.isSignUpModalOpen = true;
    },
    closeLoginModal() {
      this.isLoginModalOpen = false;
    },
    closeSignUpModal() {
      this.isSignUpModalOpen = false;
    },
    logout() {
      accountApi.logout({ user_id: this.user.userId });
      alert("로그아웃 되었습니다.");
      this.setToken(null);
      this.setUser(null);
      if (this.$route.path !== "/") this.$router.push("/");
    },
  },
  computed: {
    ...mapGetters(account, ["token", "user"]),
    stickyMode() {
      return !(this.isScrollTop && this.$route.path === "/");
    },
    isLoggedIn() {
      return this.token != null;
    },
    isAdmin() {
      return this.user && this.user.roleType === "ADMIN";
    },
    username() {
      if (!this.user) return "";
      return this.user.username;
    },
    roleType() {
      if (!this.user) return "";
      return this.user.roleType;
    },
  },
};
</script>

<style scoped>
.container {
  width: 100%;
}
</style>
