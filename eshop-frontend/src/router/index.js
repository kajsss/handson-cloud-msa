import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Login',
    component: () => import('@/components/Login.vue'),
  },

    {
    path: '/home',
    name: 'Home',
    component: () => import('@/components/Home.vue'),
  },
  {
    path: '/cart',
    name: 'Cart',
    component: () => import('@/components/Cart.vue'),
  },
  {
    path: '/products/:id',
    name: 'ProductDetail',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('@/components/ProductDetail.vue'),
    props: true
  },
  {
    path: '/order',
    name: 'Order',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('@/components/Order.vue'),
    props: true
  },
  {
    path: '/order-list',
    name: 'OrderList',
    component: () => import('@/components/OrderList.vue'),
    props: true
  }
];

const router = new VueRouter({
  routes
});

export default router
