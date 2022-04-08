import req from "./req-wrapper";

const RANK_URI = {
  RANK: "/rank",
};

export default {
  getRank(success) {
    req.get(RANK_URI.RANK, success);
  },
  
};
