import { store } from "@/store";
import axios from "axios";

const config = {
  baseUrl: "http://i6c207.p.ssafy.io:8080",
  access_token: store.getters["account/token"],
};

// 지도 색깔 불러오기
function getMapViewInfo(locId) {
  return axios({
    method: "POST",
    url: `${config.baseUrl}/info/` + locId,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${config.access_token}`,
    },
  });
}

export { getMapViewInfo };
