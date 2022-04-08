import req from "./req-wrapper";

const REVIEW_URI = {
  REVIEW: "/review",
  IMAGE: "/review/image",
  TEXT: "/review/text",
};

export default {
  getReview(travelSeq, success) {
    req.get(REVIEW_URI.REVIEW + "/" + travelSeq, success);
  },
  fileUpload(travelSeq, body, success) {
    const uri = REVIEW_URI.REVIEW + "/" + travelSeq;
    req.fileUpload(uri, body, success);
  },
  imageUpdate(travelSeq, body, success) {
    const uri = REVIEW_URI.IMAGE + "/" + travelSeq;
    req.fileUpdate(uri, body, success);
  },
  textUpdate(travelSeq, body, success) {
    const uri = REVIEW_URI.TEXT + "/" + travelSeq;
    req.put(uri, body, success);
  },
};
