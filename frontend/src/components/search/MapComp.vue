<template>
  <div class="basketmap" id="map"></div>
</template>

<script>
export default {
  data() {
    return {
      center: "",
      level: 0,
    };
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);
    },
  },
  // mounted() {
  //   console.log(process.env);

  //   const script = document.createElement("script");
  //   script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;

  //   document.head.appendChild(script);
  //   // const options = {
  //   //   //지도를 생성할 때 필요한 기본 옵션
  //   //   center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
  //   //   level: 3, //지도의 레벨(확대, 축소 정도)
  //   // };

  //   // const map = new kakao.maps.Map(container, options);
  // },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=1018f3e83e657d09ee44bd49e2d126b7";
      document.head.appendChild(script);
    }
  },
  created() {},
};
</script>

<style scoped>
.basketmap {
  background: #d8e8f2;
  float: left;
  width: 28%;
  height: 50%;
  border-radius: 20px;
}
</style>
