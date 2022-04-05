export default {
  handle(
    success,
    fail = (message) => {
      console.log(message);
    }
  ) {
    return (res) => {
      if ("header" in res.data) {
        if (res.data.header.code == 200) {
          success(res.data.body);
        } else {
          fail(res.data.header.message);
        }
      } else if (res.data.success === true) {
        success(res.data.response);
      } else fail(res.data.error);
    };
  },
};
