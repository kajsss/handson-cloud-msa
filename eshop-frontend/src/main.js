import Vue from 'vue'
import App from '@/App.vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/main.css'
import router from '@/router'
import store from '@/store'
import axios from 'axios'

Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';


Vue.prototype.$renderMoney = function(money, userCurrency) {
  if(!money) return userCurrency + " " + 0
  if(userCurrency === money.currencyCode) {
    if(money.nanos) {
      return money.currencyCode + " " + money.units + "." + (money.nanos / 1000000).toFixed(0)
    }
    return money.currencyCode + " " + money.units
  }

  axios.post(process.env.VUE_APP_BASE_URL + "currencies/convert", { from: money, to_code: userCurrency })
  .then((res) => {
    var converted = res.data;
    if(converted.nanos) {
      return userCurrency + " " + converted.units + "." + (converted.nanos / 1000000).toFixed(0)
    }
    return userCurrency + " " + converted.units
  })
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
