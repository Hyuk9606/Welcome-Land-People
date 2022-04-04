<template>
  <div class="jejumap">
    <div id="map-wrapper" class="map-wrapper"></div>
    <modal-comp v-if="showModal">
      <div slot="close">
        <i
          class="fa-regular fa-circle-xmark closeModalBtn"
          @click="showModal = false"
        ></i>
      </div>
      <h3 slot="header">"{{ area }}"</h3>
      <div slot="body">
        <span class="mapPhoto"> 사진넣기 </span>
        <span class="mapColor"> 색칠하기 </span>
      </div>
    </modal-comp>
  </div>
</template>

<script>
import * as d3 from "d3";
import ModalComp from "@/components/ModalComp.vue";

const MAP_GEOJSON = require("@/rjeju.geo.json");

export default {
  data() {
    return {
      area: "",
      showModal: false,
    };
  },
  components: {
    ModalComp,
  },
  methods: {
    drawMap() {
      // 지도정보
      const geojson = MAP_GEOJSON;

      let centered = undefined;

      // 현재의 브라우저의 크기 계산
      const divWidth = document.getElementById("map-wrapper").clientWidth;
      const width = divWidth < 1000 ? divWidth * 0.9 : 1000;
      const height = width - 300;

      // 지도를 그리기 위한 svg 생성
      const svg = d3
        // .select('.d3')
        .select(".map-wrapper")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

      // 지도가 그려지는 그래픽 노드(g) 생성
      const g = svg.append("g");
      // 지도가 그려지는 그래픽 노드
      const mapLayer = g.append("g").classed("map-layer", true);

      // 지도의 출력 방법을 선택(메로카토르)
      let projection = d3.geoMercator().scale(1).translate([0, 0]);

      // svg 그림의 크기에 따라 출력될 지도의 크기를 다시 계산
      const path = d3.geoPath().projection(projection);
      const bounds = path.bounds(geojson);
      const widthScale = (bounds[1][0] - bounds[0][0]) / width;
      const heightScale = (bounds[1][1] - bounds[0][1]) / height;
      const scale = 0.98 / Math.max(widthScale, heightScale);
      const xoffset =
        //
        width / 2 - (scale * (bounds[1][0] + bounds[0][0])) / 2 + 0;
      const yoffset =
        //
        height / 2 - (scale * (bounds[1][1] + bounds[0][1])) / 2 + 0;
      const offset = [xoffset, yoffset];
      projection.scale(scale).translate(offset);

      // 지도 색깔
      const color = d3
        //
        .scaleLinear()
        .domain([1, 20])
        .clamp(true)
        .range(["white", "white"]);

      // Get province color
      function fillFn(d) {
        //console.log(d.properties.OBJECTID);
        return color(nameLength(d));
      }

      // Get province name length
      function nameLength(d) {
        //console.log(d.properties.OBJECTID);
        const n = nameFn(d);
        //console.log("여기", n);
        return n ? n : 0;
      }

      // Get province name
      function nameFn(d) {
        //console.log(d.properties.OBJECTID);
        return d && d.properties ? d.properties.OBJECTID : null;
      }

      let modal = this;
      function clicked(d) {
        console.log("지도 눌림", d.path[0].__data__.properties.OBJECTID);
        modal.showModal = !modal.showModal;
        modal.area = d.path[0].__data__.properties.ELEC_EMD;
        //console.log(test.showModal);
      }

      function mouseover() {
        // Highlight hovered province
        d3.select(this).style("fill", "#1483ce");
      }

      function mouseout(d) {
        // Reset province color
        mapLayer.selectAll("path").style("fill", () => {
          return centered && d === centered ? "#D5708B" : fillFn(d);
        });
      }

      // 지도 그리기
      mapLayer
        .selectAll("path")
        .data(geojson.features)
        .enter()
        .append("path")
        .attr("d", path)
        .attr("vector-effect", "non-scaling-stroke")
        .style("fill", fillFn)
        .on("mouseover", mouseover)
        .on("mouseout", mouseout)
        .on("click", clicked);
    },
  },
  mounted() {
    this.drawMap();
  },
};
</script>

<style lang="scss">
.jejumap {
  float: left;
  width: 70%;
}
.map-wrapper {
  position: relative;
  text-align: center;

  .background {
    /* fill: #021019; */
    fill: transparent;
    pointer-events: all;
  }
  .map-layer {
    fill: #67757e;
    stroke: #021019;
    stroke-width: 1px;
  }
}
.mapPhoto {
  width: 19rem;
  height: 50px;
  line-height: 50px;
  background-color: #ffca43;
  border-radius: 10px;
  margin: 0 auto;
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  color: black;
  display: block;
}
.mapColor {
  width: 19rem;
  height: 50px;
  line-height: 50px;
  background-color: #89ed66;
  border-radius: 10px;
  margin: 20px auto;
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  color: black;
  display: block;
}
</style>
