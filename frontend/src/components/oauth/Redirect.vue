<template>
  <div></div>
</template>

<script>
import { mapMutations, mapActions } from "vuex";

const account = "account";

export default {
  created() {
    const token = this.$route.query.token;
    if (token) {
      this.setToken(token);
      this.fetchUser();
    }
    this.$router.replace("/");
  },
  methods: {
    ...mapActions(account, ["fetchUser"]),
    ...mapMutations(account, ["setToken"]),
  },
};
</script>
