<template>
  <header>
    <h1>&nbsp&nbsp&nbsp&nbsp Title</h1>
    <div>
      <nav class="login">
        <button v-if="!isLoggedIn" @click="$emit('onOpenLoginModal')">
          <i class="fa-solid fa-circle-user fa-2x user"></i>
        </button>

        <button v-else @click="logout">
          <!-- <span>{{ user.username }}님</span> -->
          <i class="fa-solid fa-right-from-bracket fa-2x user"></i>
        </button>
      </nav>
    </div>
  </header>
</template>

<script>
import $ from "@/utils";
import { mapGetters, mapActions } from "vuex";

const account = "account";

export default {
  created() {
    this.isLoggedIn && this.fetchUser();
  },
  methods: {
    ...mapActions(account, ["fetchUser"]),
    go(path) {
      this.close();
      if (this.$route.path !== path) this.$router.push(path);
    },
    close() {
      this.isOpenAccountDropdown = false;
    },
    logout() {
      this.close();
      this.$emit("onLogout");
    },
  },
  computed: {
    ...mapGetters(account, ["token", "user"]),
    isLoggedIn() {
      return this.token != null;
    },
    isAdmin() {
      return this.user && this.user.roleType === "ROLE_ADMIN";
    },
  },
  directives: {
    "click-outside": $.clickOutside(),
  },
};
</script>

<style scoped>
.login {
  float: left;
  position: relative;
  width: 5%;
  height: 70px;
  margin: 2.5rem 0 1.5rem;
}
.user {
  position: static;
  bottom: 0px;
}
h1 {
  float: left;
  width: 95%;
  color: #000000;
  font-weight: 1000;
  font-size: 70px;
  margin: 2.5rem 0 1.5rem;
  text-align: center;
}
</style>
