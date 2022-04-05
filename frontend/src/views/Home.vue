<template>
  <div class="home">
    <nav-bar
      :stickyMode="stickyMode"
      @onOpenLoginModal="openLoginModal"
      @onOpenSignUpModal="openSignUpModal"
      @onLogout="logout"
    />
    <router-view />
    <login-modal
      v-if="isLoginModalOpen"
      :isOpen="isLoginModalOpen"
      @onCloseModal="closeLoginModal"
    />
    <sign-up-modal
      v-if="isSignUpModalOpen"
      :isOpen="isSignUpModalOpen"
      @onCloseModal="closeSignUpModal"
    />

    <div class="bp-main-container">
      <section class="main-top-section">
        <p>socialLogin</p>
        <p v-if="isLoggedIn">userName : {{ username }}</p>
        <p v-if="isLoggedIn">role : {{ roleType }}</p>
      </section>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import LoginModal from "@/components/user/LoginModal";
import signUpModal from "@/components/user/signUpModal";
import accountApi from "../api/account";
import { mapGetters, mapMutations } from "vuex";

const account = "account";

export default {
  name: "Home",
  components: { NavBar, LoginModal, signUpModal },
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
    window.onload = function () {};
  },
  methods: {
    ...mapMutations(account, ["setToken", "setUser"]),
    openLoginModal() {
      this.isLoginModalOpen = true;
    },
    openSignUpModal() {
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
.bp-main-container .main-top-section {
  width: 100%;
  height: 320px;
  padding-top: 5rem;
}

@media screen and (min-width: 768px) {
  .bp-main-container .main-top-section .slogan-container {
    padding-top: 70px;
  }
}
</style>
