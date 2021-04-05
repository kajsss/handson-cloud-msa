<template>
  <b-container>
    <b-jumbotron class="text-center mb-0" :style="{backgroundColor: bannercolor}">
      <b-container>
        <h1 class="jumbotron-heading">
            One-stop for Hipster Fashion &amp; Style Online
        </h1>
        <p class="lead text-muted">
            Tired of mainstream fashion ideas, popular trends and
            societal norms? This line of lifestyle products will help
            you catch up with the hipster trend and express your
            personal style. Start shopping hip and vintage items now!
        </p>
      </b-container>
    </b-jumbotron>

    <b-container class="py-5 bg-light">
        <Products />
        <Ad />
    </b-container>
  </b-container>
</template>

<script>
import {mapState} from 'vuex'

import Ad from '@/components/Ad.vue'
import Products from '@/components/Products.vue'

export default {
  name: 'Home',
  components: {
    Ad,
    Products
  },
  mounted:function(){
    const token = localStorage.getItem('token');
    if(token === null || token === ''){
      localStorage.removeItem('token');
      this.$router.push('/');
    }
  },
  computed: {
    ...mapState({
      bannercolor: state => state.bannercolor
    })
  }
}
</script>
