<template>
  <div>
    <router-link to="/">메인 페이지로 이동</router-link>

    <router-link to="/list">여행목록 페이지로 이동</router-link>

    <router-link to="/jim">장바구니 페이지로 이동</router-link>

    <router-link to="/plan">계획짜기 페이지로 이동</router-link>

    <router-link to="/review">방문기 작성 페이지로 이동</router-link>
  </div>
</template>

<script>
export default {
  name: "ToolBar",
};
</script>

<style></style>
