import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      name: 'login',
      meta: { layout: 'empty' },
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      meta: { layout: 'empty' },
      component: () => import('../views/Register.vue')
    },
    {
      path: '/categories',
      name: 'categories',
      meta: { layout: 'main', auth: true },
      component: () => import('../views/Categories.vue')
    },
    {
      path: '/',
      name: 'home',
      meta: { layout: 'main', auth: true },
      component: () => import('../views/Home.vue')
    },
    {
      path: '/record',
      name: 'record',
      meta: { layout: 'main', auth: true },
      component: () => import('../views/NewRecord.vue')
    },
    {
      path: '/history',
      name: 'history',
      meta: { layout: 'main', auth: true },
      component: () => import('../views/History.vue')
    },
    {
      path: '/detail/:id',
      name: 'detail',
      meta: { layout: 'main', auth: true },
      component: () => import('../views/Detail.vue')
    },
    {
      path: '*',
      name: 'error',
      meta: { layout: 'main', auth: true },
      component: () => import('../views/Home.vue')
    }
  ]
})

router.beforeEach((to, from, next) => {
  const nameFromSessionStorage = sessionStorage.getItem('name')
  const tokenFromSessionStorage = sessionStorage.getItem('token')

  const currentUser = store.getters.getToken

  const requireAuth = to.matched.some(record => record.meta.auth)
  if (requireAuth && !currentUser && !tokenFromSessionStorage) {
    next('/login?message=login')
  } else {
    if (tokenFromSessionStorage) {
      store.commit('login', {
        name: nameFromSessionStorage,
        token: tokenFromSessionStorage
      })
    }
    next()
  }
})

export default router
