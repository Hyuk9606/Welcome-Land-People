import req from "./req-wrapper";

const ACCOUNT_URI = {
  LOGIN: "/auth/login",
  USER: "/users",
  JOIN: "/users/join",
  LOGOUT: "/users/logout",
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
};
