import req from "./req-wrapper";

const ACCOUNT_URI = {
  LOGIN: "/auth/login",
  USER: "/users",
  JOIN: "/users/join",
  LOGOUT: "/users/logout",
  LIKE: "/users/like",
  LIKE_CONTENTS: "/users/contents",
  DISLIKE: "/users/dislike",
};

export default {
  login(body, success, fail) {
    req.post(ACCOUNT_URI.LOGIN, body, success, fail);
  },
  getUser(success) {
    req.get(ACCOUNT_URI.USER, success);
  },
  logout(body, success) {
    req.post(ACCOUNT_URI.LOGOUT, body, success);
  },
  register(body, success, fail) {
    req.join(ACCOUNT_URI.JOIN, body, success, fail);
  },
  likeContent(contentsId, success) {
    console.log("like");
    const uri = ACCOUNT_URI.LIKE + "/" + contentsId;
    req.get(uri, success);
  },
  dislikeContent(contentsId, success) {
    console.log("dislike");
    const uri = ACCOUNT_URI.DISLIKE + "/" + contentsId;
    req.get(uri, success);
  },
  getLikeContents(success) {
    req.get(ACCOUNT_URI.LIKE_CONTENTS, success);
  },
};
