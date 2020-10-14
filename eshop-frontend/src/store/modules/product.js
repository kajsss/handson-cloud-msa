import axios from 'axios'

export default {
    namespaced: true,
    state: {
      products: [],
      currentProduct: {
        id: "",
        "name": "",
        description: "",
        picture: ""
      }
    },
    getters: {
      products: state => state.products,
      product: state => state.currentProduct
    },
    actions: {
      async fetchProducts(context) {
        console.log("fetchProducts")
        const res = await axios.get(process.env.VUE_APP_BASE_URL + "/products")
        context.commit("setProducts", res.data.products)
      },
      async fetchProduct(context, id) {
        console.log("fetchProduct : " + id)
        const res = await axios.get(process.env.VUE_APP_BASE_URL + "/products/" + id)
        context.commit("setCurrentProduct", res.data)
      }
    },
    mutations: {
      setProducts(state, products) {
        state.products = products 
      },
      setCurrentProduct(state, product) {
        state.currentProduct = product 
      }
    }
}