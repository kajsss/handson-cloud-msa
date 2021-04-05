<template>
  <b-container>
    <b-jumbotron class="text-center mb-0">
      <b-container>
        <h1 class="jumbotron-heading">
            Welcome! HipsterShop
        </h1>
      </b-container>
    </b-jumbotron>
    <br><br>
    <b-row class="justify-content-md-center">
      <b-col cols="7">
        <b-card style="max-width: 40rem;" title="Login">
          <b-input
              id="input-1"
              v-model="email"
              required
              placeholder="Enter UserEmail"></b-input>
          <br>
          <b-input
              id="input-2"
              v-model="password"
              required
              placeholder="Enter Password"></b-input>
        </b-card>
      </b-col>
    </b-row>
    <br>
    <b-row class="justify-content-md-center">
      <b-col cols="1">
        <b-button variant="primary" @click="login()">Login</b-button>
      </b-col>
      <b-col cols="1">
        <b-button variant="danger" @click="onReset()" class="ml-2">Reset</b-button>
      </b-col>

    </b-row>


  </b-container>
</template>

<script>
import {loginHelper} from "@/helper/LoginHelper";

export default {
  name: 'Login',
  components: {
  },
  mounted:function(){
    const token = localStorage.getItem('token');
    if(token === null || token === ''){
      localStorage.removeItem('token');
      this.$router.push('/');
    }else {
      this.$router.push('/home');
    }
  },
  methods: {
    login: async function (){
      const user = await loginHelper.login(this.email, this.password);
      if(user) {
        await this.$router.push('/home');
      }else {
        alert('Login Failed! ');
      }
    },
    onReset: function (){
      this.email = '';
      this.password = '';
    }
  },
  data: function () {
    return {
      email: '',
      password: '',
    }
  },
  computed: {
  }
}
</script>
