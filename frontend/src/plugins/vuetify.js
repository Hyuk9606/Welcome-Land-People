import Vue from "vue";
import Vuetify from "vuetify/lib/framework";
import colors from "vuetify/lib/util/colors";

Vue.use(Vuetify);

export default new Vuetify({
      // 아래 방식을 이용하면 아이콘 안받아와짐
  icons: {
    iconfont: "mdiSvg",
  },
  theme: {
    theme: {
      light: {
        primary: colors.red.darken1, // #E53935
        secondary: colors.red.lighten4, // #FFCDD2
        accent: colors.indigo.base, // #3F51B5
      },
    },
  },
});
