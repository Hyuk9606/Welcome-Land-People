import req from "./req-wrapper";

const JEJUMAP_URI = {
  MAPCOLOR: "/mapview/color/",
  MAPIMG: "/mapview/image/",
  ALLMAP: "/mapview/info",
  DELMAP: "/mapview/",
};

export default {
  inputColor(data, body, success, fail) {
    req.post(JEJUMAP_URI.MAPCOLOR + data.loc, body, success, fail, {});
  },
  inputImg(data, body, success, fail) {
    req.post(JEJUMAP_URI.MAPIMG + data.loc, body, success, fail, {});
  },
  infoMap(success, fail) {
    req.get(JEJUMAP_URI.ALLMAP, success, fail, {});
  },
  delMap(loc, body, success, fail) {
    req.delete(JEJUMAP_URI.DELMAP + loc, body, success, fail);
  },
};
