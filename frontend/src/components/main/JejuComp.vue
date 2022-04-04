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
      <div v-if="selectPhoto" slot="body">
        <div>미리보기</div>
        <div>
          <p>이미지 조정하기</p>
        </div>
        <div>
          <p>이미지를 직접 움직이거나</p>
          <p>아래 설정을 통해 편집할 수 있습니다.</p>
        </div>
        <div>
          <input type="file" />
        </div>
        <div class="ralign">
          <button class="okBtn" @click="close">선택 완료</button>
        </div>
      </div>
      <div v-if="selectColor" slot="body">
        <div>미리보기</div>
        <div>
          <span class="c-41ac6c" @click="changeColor"></span>
          <span class="c-ffca43" @click="changeColor"></span>
          <span class="c-ff5959" @click="changeColor"></span>
          <span class="c-339dff" @click="changeColor"></span>
          <span class="c-000000" @click="changeColor"></span>
        </div>
        <div>
          <span class="c-b68fe9" @click="changeColor"></span>
          <span class="c-67f3c9" @click="changeColor"></span>
          <span class="c-ee8041" @click="changeColor"></span>
          <span class="c-f393db" @click="changeColor"></span>
          <span class="remove" @click="changeColor"><i class="c-ffffff fa-2x fa-solid fa-ban"></i></span>
        </div>
        <div class="ralign">
          <button class="okBtn" @click="close">선택 완료</button>
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
        3394: { colr: "#ffffff" },
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
      this.colors[this.areaCode].colr = ncolor;
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
      // const effectLayer = g.append('g').classed('effect-layer', true);
      // 지도가 그려지는 그래픽 노드
      const mapLayer = g.append("g").classed("map-layer", true);

      // 지도의 출력 방법을 선택(메로카토르)
      let projection = d3.geoMercator().scale(1).translate([0, 0]);

      // svg 그림의 크기에 따라 출력될 지도의 크기를 다시 계산
      const path = d3.geoPath().projection(projection);
      const bounds = path.bounds(MAP_GEOJSON);
      const widthScale = (bounds[1][0] - bounds[0][0]) / width;
      const heightScale = (bounds[1][1] - bounds[0][1]) / height;
      const scale = 0.95 / Math.max(widthScale, heightScale);
      const xoffset = width / 2 - (scale * (bounds[1][0] + bounds[0][0])) / 2 + 0;
      const yoffset = height / 2 - (scale * (bounds[1][1] + bounds[0][1])) / 2 + 0;
      const offset = [xoffset, yoffset];
      projection.scale(scale).translate(offset);

      // function 안에서 사용할 this값 고정
      const _this = this;

      function fillFn(d) {
        const pcolor = _this.partyColor(d.properties.OBJECTID);
        return pcolor;
      }

      function clicked(d) {
        _this.showModal = !_this.showModal;
        _this.area = d.path[0].__data__.properties.ELEC_EMD;
        _this.areaCode = d.path[0].__data__.properties.OBJECTID;
        _this.current = this;
      }

      function mouseover() {
        d3.select(this).style("fill", "#1483ce");
      }

      function mouseout() {
        mapLayer.selectAll("path").style("fill", (d) => {
          return fillFn(d);
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

  .map-layer {
    fill: #67757e;
    stroke: #021019;
    stroke-width: 1px;
    cursor: pointer;
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
  cursor: pointer;
}
.mapPhoto:hover {
  background-color: #e4b43b;
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
  cursor: pointer;
}
.mapColor:hover {
  background-color: #69ba4c;
}
.ralign {
  text-align: right;
}
.okBtn {
  width: 100px;
  height: 40px;
  margin-top: 30px;
  color: white;
  background: #339dff;
  border-radius: 20px;
  cursor: pointer;
}
.okBtn:hover {
  background-color: #277ac8;
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
  cursor: pointer;
}

// 색깔 선택 시 이벤트
// .c-41ac6c:active {
//   border-style: solid;
//   color: white;
// }

.c-ffca43 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #ffca43;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.c-ff5959 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #ff5959;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.c-339dff {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #339dff;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.remove i {
  float: left;
  width: 2rem;
  height: 2rem;
  margin: 12px;
  background: #41ac6c;
}
.c-ffffff {
  color: red;
  background-color: #ffffff;
  cursor: pointer;
}
.c-f393db {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #f393db;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.c-ee8041 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #ee8041;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.c-67f3c9 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #67f3c9;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.c-b68fe9 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #b68fe9;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
.c-000000 {
  float: left;
  width: 2rem;
  height: 2rem;
  line-height: 80px;
  background-color: #000000;
  border-radius: 50%;
  margin: 12px;
  cursor: pointer;
}
</style>
