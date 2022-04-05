//import { getMapViewInfo } from "@/api/jejumap";

const state = {
  mapColors: {
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
};

const getters = {
  getColors(state) {
    return state.mapColors;
  },
};

const mutations = {
  loadColors(state) {
    console.log("store 들어옴");
    state.mapColors = {};
  },
  //mapView 정보 조회
  //   export function getMapViewInfo(locId) {
  //     return axios({
  //       method: "POST",
  //       url: API_BASE_URL + "/info/" + locId,
  //       headers: {
  //         "Content-Type": "application/json",
  //         "Authorization" : "Bearer " + ACCESS_TOKEN,
  //       }
  //     });
  // }
};

const actions = {
  GET_MAPVIEWINFO() {
    // getMapViewInfo()
    //   .then((res) => {
    //     console.log(res);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  },
};

const mainView = {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};

export default mainView;
