<!-- vuetify 아이콘을 사용하려면 public/index.html head 안에  cdn 설치 -->
<template>
  <div>
    <form method="POST" action="#" enctype="multipart/form-data">
      <!-- 이미지를 선택하세요! :
      <input type="file" name="image" accept="image/*" multiple />
      <br />
      방문기를 작성하세요! : <input type="text" name="title" />
      <br />
      <br /> -->
      <!-- <input type="submit" value="Upload" /> -->
      <h2>이미지를 선택하세요!</h2>
      <v-file-input id="file" multiple rounded label="이미지를 선택하세요!" filled prepend-icon="mdi-camera" style="margin: 20px"></v-file-input>
      <h2>내용을 입력하세요!</h2>
      <v-container fluid>
        <v-textarea v-model="text" rounded name="input-7-1" filled placeholder="내용을 입력하세요!" style="margin-right: 10px; margin-left: 10px" height="200px" value=""></v-textarea>
      </v-container>
      <v-btn @click="doPost" rounded class="button_style button_position" style="background: rgb(111, 117, 121); color: white">작성 완료</v-btn>
      <v-btn @click="gotoBegin" rounded class="button_style button_position" style="background: rgb(111, 117, 121); color: white">돌아가기</v-btn>
    </form>
  </div>
</template>

<script>
import reviewApi from "@/api/review";
export default {
  name: "ReviewCreateForm",
  data() {
    return {
      currentTravel: "",
      text: "",
    };
  },
  methods: {
    gotoBegin: function () {
      console.log("작성 페이지에서 돌아가기 버튼을 눌렀습니다");
      this.$router.push("/list");
    },
    doPost() {
      const file = document.getElementById("file");

      console.log(this.text);
      const formData = new FormData();
      Array.from(file.files).forEach((img) => formData.append("image", img));

      formData.append("text", this.text);
      console.log(formData);
      reviewApi.fileUpload(this.currentTravel, formData, (res) => {
        console.log(res);
        this.$router.push("/list");
      });
    },
  },
  created() {
    this.currentTravel = this.$route.query.travelSeq;
    this.postURI = "http://j6c207.p.ssafy.io:8080/api/review/" + this.currentTravel;
  },
};
</script>

<style scoped>
h2 {
  margin-bottom: 5px;
}
.button_style {
  color: white; /** 안먹음 */
  font-weight: bold;
  background: rgb(111, 117, 121); /** 안먹음 */
  margin-left: 0px; /* 영역 겉쪽 여유 공간 .. 이건 먹음 */
  margin-right: 20px; /* 영역 겉쪽 여유 공간 .. 이건 먹음 */
  padding: 10px; /** 영역 안쪽 여유 공간 .. 이거 안먹음 */
}
.button_position {
  /** 진짜 이거 혁명적이다 float... 어떻게 배치해야 될지 오만 고민 다했는데 ㅠㅠㅠ */
  float: right;
}
</style>
