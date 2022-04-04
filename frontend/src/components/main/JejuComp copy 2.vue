<template>
  <div class="jejumap">
    <div id="map-wrapper" class="map-wrapper"></div>
    <modal-comp v-if="showModal">
      <div slot="close">
        <i class="fa-regular fa-circle-xmark closeModalBtn" @click="close"></i>
      </div>
      <h3 slot="header">"{{ area }}"</h3>
      <div v-if="selectFill" slot="body">
        <span class="mapPhoto" @click="inputPhoto"> 사진넣기 </span>
        <span class="mapColor" @click="inputColor"> 색칠하기 </span>
      </div>
      <div v-if="selectPhoto" slot="body">사진을 넣어보자</div>
      <div v-if="selectColor" slot="body">
        <div>미리보기</div>
        <div>
          <span class="c-41ac6c" @click="changeColor"></span>
          <span class="c-ffca43" @click="changeColor"></span>
          <span class="c-ff5959" @click="changeColor"></span>
          <span class="c-339dff" @click="changeColor"></span>
          <span class="c-a4aaaf" @click="changeColor"></span>
        </div>
        <div>
          <span class="c-b68fe9" @click="changeColor"></span>
          <span class="c-67f3c9" @click="changeColor"></span>
          <span class="c-ee8041" @click="changeColor"></span>
          <span class="c-f393db" @click="changeColor"></span>
          <span class="c-000000" @click="changeColor"></span>
        </div>
        <div>
          <button>선택 완료</button>
        </div>
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
      areaCode: 0,
      centered: 0,
      colors: {
        3394: { colr: "#f5ff38" },
        3395: { colr: "#ffffff" },
        3396: { colr: "#ffffff" },
        3397: { colr: "#ffffff" },
        3398: { colr: "#ffffff" },
        3400: { colr: "#ffffff" },
        3401: { colr: "#ffffff" },
        3420: { colr: "#ffffff" },
        3421: { colr: "#ffffff" },
        3422: { colr: "#ffffff" },
        3423: { colr: "#ffffff" },
        3424: { colr: "#ffffff" },
        3425: { colr: "#ffffff" },
      },
      showModal: false,
      selectFill: true,
      selectPhoto: false,
      selectColor: false,
      current: "",
    };
  },
  components: {
    ModalComp,
  },
  methods: {
    changeColor(d) {
      const color = d.path[0]._prevClass;
      const ncolor = "#" + color.substring(2, 8);
      d3.select(this.current).style("fill", ncolor);
      this.colors[this.colors.length] = { objid: this.areaCode, colr: ncolor };
    },
    close() {
      this.showModal = false;
      this.selectFill = true;
      this.selectPhoto = false;
      this.selectColor = false;
    },
    inputColor() {
      this.selectFill = false;
      this.selectColor = true;
    },
    inputPhoto() {
      this.selectFill = false;
      this.selectPhoto = true;
    },
    partyColor(code) {
      let color = null;
      const localSeatCode = code;
      if (localSeatCode in this.colors) {
        color = this.colors[localSeatCode].colr;
      }
      return color;
    },
    drawMap() {
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
      const bounds = path.bounds(MAP_GEOJSON);
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
      // this.color = d3
      //   //
      //   .scaleLinear()
      //   .domain([1, 20])
      //   .clamp(true)
      //   .range(["#ffffff", "#ffffff"]);

      const fill = this;
      function mouseover() {
        fill.current = this;
        d3.select(this).style("fill", "#a8a9ad");
      }

      function mouseout(d) {
        mapLayer.selectAll("path").style("fill", () => {
          return fill.fillFn(d.path[0].__data__);
        });
      }

      // 지도 그리기
      mapLayer
        .selectAll("path")
        .data(MAP_GEOJSON.features)
        .enter()
        .append("path")
        .attr("d", path)
        .attr("vector-effect", "non-scaling-stroke")
        .style("fill", this.fillFn)
        .on("mouseover", mouseover)
        .on("mouseout", mouseout)
        .on("click", this.clicked);
    },
    // Get color
    fillFn(d) {
      const pcolor = this.partyColor(d.properties.OBJECTID);
      console.log(pcolor);
      return pcolor;

      //return this.color(this.nameLength(d));
    },

    // Get name length
    nameLength(d) {
      const n = this.nameFn(d);
      return n ? n : 0;
    },

    // Get name
    nameFn(d) {
      return d && d.properties ? d.properties.OBJECTID : null;
    },

    clicked(d) {
      this.showModal = !this.showModal;
      this.area = d.path[0].__data__.properties.ELEC_EMD;
      this.areaCode = d.path[0].__data__.properties.OBJECTID;
    },
  },
  mounted() {
    this.drawMap();
  },
};
</script>

<style lang="scss" scope>
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

/*지도 색깔 선택*/
.c-41ac6c {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #41ac6c;
  border-radius: 50%;
  margin: 12px;
}
.c-ffca43 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #ffca43;
  border-radius: 50%;
  margin: 12px;
}
.c-ff5959 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #ff5959;
  border-radius: 50%;
  margin: 12px;
}
.c-339dff {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #339dff;
  border-radius: 50%;
  margin: 12px;
}
.c-a4aaaf {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #a4aaaf;
  border-radius: 50%;
  margin: 12px;
}
.c-f393db {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #f393db;
  border-radius: 50%;
  margin: 12px;
}
.c-ee8041 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #ee8041;
  border-radius: 50%;
  margin: 12px;
}
.c-67f3c9 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #67f3c9;
  border-radius: 50%;
  margin: 12px;
}
.c-b68fe9 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #b68fe9;
  border-radius: 50%;
  margin: 12px;
}
.c-000000 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #000000;
  border-radius: 50%;
  margin: 12px;
}
</style>
