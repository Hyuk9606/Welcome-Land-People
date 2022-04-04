<template>
  <div class="stacy">
    <!-- 방문기 수정 컴포넌트 -->
    <h1>나만의 애월 여행 방문기를 수정하세요!</h1>
    <br />
    <br />
    <review-update-form></review-update-form>
  </div>
</template>

<script>
import ReviewUpdateForm from "@/components/review/form/ReviewUpdateForm.vue";

export default {
  name: "ReviewUpdate",
  components: {
    ReviewUpdateForm,
  },
};
</script>

<style>
.stacy {
  padding: 20px;
}
h2 {
  margin: 20px;
}
</style>
