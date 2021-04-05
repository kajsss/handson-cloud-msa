<template>
  <b-navbar type="dark" variant="dark">
    <b-navbar-brand href="/">Hipster Shop</b-navbar-brand>
    <b-navbar-nav class="ml-auto">
      <b-nav-form v-if="currencies" method="POST" action="/setCurrency" id="currency_form">
        <b-form-select @change="setUserCurrency" :value="userCurrency" :options="currencies" style="width:auto;"></b-form-select>
        <router-link class="btn btn-primary btn-light ml-2" to="/cart" role="button">View Cart ({{cartSize}})</router-link>
        <router-link class="btn btn-primary btn-light ml-2" to="/order-list" role="button">My Orders ({{ordersCount}})</router-link>
        <b-button variant="primary" class="btn btn-primary btn-light ml-2" @click="logout()">Logout</b-button>
      </b-nav-form>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'


export default {
  name: 'Header',
  data: function(){
    return {
    }
  },
  computed: mapGetters({
    currencies: 'currency/currencies',
    userCurrency: 'currency/userCurrency',
    cartSize: 'cart/cartSize',
    ordersCount: 'order/ordersCount'
  }),
  async mounted() {
    this.fetchCurrencies();
    this.fetchCartItems();
    await this.fetchOrders();
  },
  methods: {
    ...mapActions('cart', ['fetchCartItems']),
    ...mapActions('currency', ['setUserCurrency', 'fetchCurrencies']),
    ...mapActions('order', ['fetchOrders']),
    logout: function() {
      localStorage.removeItem('token');
      this.$router.push('/');
    },
  }
}
</script>
