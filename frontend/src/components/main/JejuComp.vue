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
        <div>
          <form id="formElem" enctype="multipart/form-data">
            <input type="file" class="hidden_input" id="reviewImageFileOpenInput" accept="image/*" multiple />
          </form>
        </div>
        <div class="ralign">
          <button class="okBtn" @click="imgClose">선택 완료</button>
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
          <button class="okBtn" @click="colorClose">선택 완료</button>
        </div>
      </div>
    </modal-comp>
    <div id="img-wrapper" class="img-wrapper"></div>
  </div>
</template>

<script>
import * as d3 from "d3";
import ModalComp from "@/components/ModalComp.vue";
import jejumapApi from "@/api/jejumap";
import { mapGetters } from "vuex";

const MAP_GEOJSON = require("@/rjeju.geo.json");
const account = "account";

export default {
  data() {
    return {
      area: "",
      areaCode: 0,
      centered: 0,
      mapInput: {
        3394: { type: "color", in: "#ffffff" },
        3396: { type: "color", in: "#ffffff" },
        3397: { type: "color", in: "#ffffff" },
        3398: { type: "color", in: "#ffffff" },
        3400: { type: "color", in: "#ffffff" },
        3401: { type: "color", in: "#ffffff" },
        3395: { type: "color", in: "#ffffff" },
        3420: { type: "color", in: "#ffffff" },
        3421: { type: "color", in: "#ffffff" },
        3422: { type: "color", in: "#ffffff" },
        3423: { type: "color", in: "#ffffff" },
        3424: { type: "color", in: "#ffffff" },
        3425: { type: "color", in: "#ffffff" },
      },
      mapImg: {
        3394: { file: "" },
        3396: { file: "" },
        3397: { file: "" },
        3398: { file: "" },
        3400: { file: "" },
        3401: { file: "" },
        3395: { file: "" },
        3420: { file: "" },
        3421: { file: "" },
        3422: { file: "" },
        3423: { file: "" },
        3424: { file: "" },
        3425: { file: "" },
      },
      tempIn: {},
      showModal: false,
      selectFill: true,
      selectPhoto: false,
      selectColor: false,
      current: "",
      isLogined: false,
    };
  },
  components: {
    ModalComp,
  },
  methods: {
    changeColor(d) {
      const color = d.path[0]._prevClass;
      this.tempIn = "#" + color.substring(2, 8);
    },
    close() {
      this.showModal = false;
      this.selectFill = true;
      this.selectPhoto = false;
      this.selectColor = false;
    },
    imgClose() {
      var formData = new FormData();
      formData.append("image", document.getElementById("formElem")[0].files[0]);
      jejumapApi.inputImg(
        {
          loc: this.areaCode,
        },
        formData,
        (body) => {
          console.log("이미지성공", body);
          this.start();
        },
        (err) => {
          console.log("이미지실패", err);
        }
      );

      this.showModal = false;
      this.selectFill = true;
      this.selectPhoto = false;
      this.selectColor = false;
    },
    colorClose() {
      jejumapApi.inputColor(
        {
          loc: this.areaCode,
        },
        {
          color: this.tempIn,
        },
        (body) => {
          console.log("색칠성공", body);
          d3.select(this.current).style("fill", this.tempIn);
          this.mapInput[this.areaCode].type = "color";
          this.mapInput[this.areaCode].in = this.tempIn;
        },
        (err) => {
          console.log("색칠실패", err);
        }
      );

      this.showModal = false;
      this.selectFill = true;
      this.selectPhoto = false;
      this.selectColor = false;
    },
    inputColor() {
      this.selectFill = false;
      this.selectColor = true;
      this.mapInput[this.areaCode].type = "color";
    },
    inputPhoto() {
      this.selectFill = false;
      this.selectPhoto = true;
      this.mapInput[this.areaCode].type = "img";
    },
    partyColor(code) {
      let color = null;
      const localSeatCode = code;
      if (localSeatCode in this.mapInput) {
        color = this.mapInput[localSeatCode].in;
        //console.log("색상", color);
      }
      return color;
    },
    drawMap() {
      // 현재의 브라우저의 크기 계산
      const divWidth = document.getElementById("map-wrapper").clientWidth;
      const width = divWidth < 1000 ? divWidth * 0.9 : 1000;
      const height = width - 200;

      // 지도를 그리기 위한 svg 생성
      const svg = d3
        // .select('.d3')
        .select(".map-wrapper")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

      // const test = d3.select(".test-wrapper").append("svg");

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
        //console.log(pcolor);
        return pcolor;
      }

      function areaFn(d) {
        //console.log(d);
        const code = d.properties.OBJECTID;
        //console.log(code);
        return code;
      }

      function fileFn(d) {
        const code = d.properties.OBJECTID;
        const imgfile = "data:image/png;base64," + _this.mapImg[code].file;
        //console.log("imgfile 받음?", _this.mapImg[code].file);

        return imgfile;
      }

      function clicked(d) {
        _this.showModal = !_this.showModal;
        _this.area = d.path[0].__data__.properties.ELEC_EMD;
        _this.areaCode = d.path[0].__data__.properties.OBJECTID;
        _this.current = this;
        //console.log(_this.areaCode);
      }

      function mouseover() {
        d3.select(this).style("fill", "#1483ce");
      }

      function mouseout() {
        mapLayer.selectAll("path").style("fill", (d) => {
          return fillFn(d);
        });
      }

      d3.select(".img-wrapper")
        .selectAll("svg")
        .data(MAP_GEOJSON.features)
        .enter()
        .append("svg")
        .attr("id", "mySvg")
        .attr("width", "0")
        .attr("height", "0")
        .append("defs")
        .attr("id", "mdef")
        .append("pattern")
        .attr("id", areaFn)
        .attr("x", 0)
        .attr("y", 0)
        .attr("width", "100%")
        .attr("height", "100%")
        .append("image")
        .attr("width", "220px")
        .attr("height", "220px")
        .attr("x", 0)
        .attr("y", -35)
        .attr("xlink:href", fileFn);

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
    start() {
      jejumapApi.infoMap(
        (res) => {
          //console.log("불러오기 성공", res);
          for (var c = 0; c < res.length; c++) {
            this.mapInput[res[c].loc].type = res[c].mapType;
            if (res[c].mapType === "color") {
              this.mapInput[res[c].loc].in = res[c].color;
            } else {
              //this.mapInput[res[c].loc].in = res[c].image.data;
              this.mapInput[res[c].loc].in = "url(#" + res[c].loc + ")";
              this.mapImg[res[c].loc].file = res[c].image.data;
              //console.log(URL.createObjectURL(this.mapInput[res[c].loc].in));
            }
            if (c === res.length - 1) {
              this.isLogined = !this.isLogined;
            }
          }
          //this.drawMap();
        },
        (err) => {
          console.log("불러오기 실패", err);
          //this.drawMap();
        }
      );
    },
  },
  mounted() {
    //console.log("mounted 실행됨");
    this.drawMap();
  },
  computed: {
    ...mapGetters(account, ["user"]),
    ...mapGetters(account, ["token"]),
  },
  watch: {
    isLogined() {
      const paths = d3.selectAll("path")._groups[0];
      const patterns = d3.selectAll("pattern")._groups[0];
      //console.log(patterns.select("#3395"));
      for (var i = 0; i < 13; i++) {
        const code = paths[i].__data__.properties.OBJECTID;
        if (this.mapInput[code].type === "image") {
          //console.log("이거", this.mapImg[code].file);
          const filename = "data:image/png;base64," + this.mapImg[code].file;
          d3.select(patterns[i])
            // .attr("x", 0)
            // .attr("y", 0)
            // .attr("width", "100%")
            // .attr("height", "100%")
            .select("image")
            // .attr("width", "200px")
            // .attr("height", "200px")
            // .attr("x", 0)
            // .attr("y", -30)
            .attr("xlink:href", filename);
        }
        d3.select(paths[i]).style("fill", this.mapInput[code].in);
      }
    },
    token(value) {
      //console.log(value);
      if (value === null) {
        d3.selectAll("path").style("fill", "#ffffff");
        for (var i = 0; i < 13; i++) {
          const code = MAP_GEOJSON.features[i].properties.OBJECTID;
          this.mapInput[code].type = "color";
          this.mapInput[code].in = "#ffffff";
          this.mapImg[code].file = "";
        }
      } else {
        this.start();
      }
    },
  },
  created() {
    //console.log("create 실행됨");
    this.start();
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
}
.c-ffffff {
  color: red;
  // background-color: #ffffff;
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
