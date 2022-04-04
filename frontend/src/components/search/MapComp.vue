<template>
  <div class="basketmap" id="map">
    카카오 지도 api
    <div>여기</div>
    <div>hi</div>
    <div>hello</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      center: "",
      level: 0,
    };
  },
  methods: {},
  mounted() {
    console.log(process.env);

    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;

    document.head.appendChild(script);
    // const options = {
    //   //지도를 생성할 때 필요한 기본 옵션
    //   center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
    //   level: 3, //지도의 레벨(확대, 축소 정도)
    // };

    // const map = new kakao.maps.Map(container, options);
  },
  created() {},
};
</script>

<style scoped>
.basketmap {
  background: red;
  float: left;
  width: 28%;
  height: 50%;
}
</style>
