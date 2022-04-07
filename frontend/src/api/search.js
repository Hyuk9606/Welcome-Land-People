import req from "./req-wrapper";

const SEARCH_URI = {
  SEARCH: "/contents/search",
};

export default {
  search(find, success) {
    const uri =
      SEARCH_URI.SEARCH +
      "/" +
      find.selected +
      "/" +
      find.label +
      "/" +
      find.query;
    console.log(uri);
    req.get(uri, success);
  },
};
