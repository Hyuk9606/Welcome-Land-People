<template>
  <div class="stacy">
    <!-- 방문기 작성 컴포넌트 -->
    <h1>나만의 애월 여행 방문기를 작성하세요!</h1>
    <br />
    <br />
    <review-create-form></review-create-form>
    <!-- <image-upload-form></image-upload-form> -->
    <!-- <text-upload-form></text-upload-form> -->
  </div>
</template>

<script>
// import ImageUploadForm from "@/components/review/form/ImageUploadForm.vue";
// import TextUploadForm from "@/components/review/form/TextUploadForm.vue";
import ReviewCreateForm from "@/components/review/form/ReviewCreateForm.vue";

export default {
  name: "ReviewCreate",
  components: {
    // ImageUploadForm,
    // TextUploadForm,
    ReviewCreateForm,
  },
};
</script>

<style>
.stacy {
  padding: 20px;
}
</style>
