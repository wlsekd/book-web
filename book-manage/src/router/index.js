import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: resolve => require(['../pages/Login.vue'], resolve)
    },
    {
      path:'/Home',
      component:resolve=>require(['../components/Home.vue'],resolve),
      children:[
        {
          path:'/Info',
          component:resolve=>require(['../pages/InfoPage.vue'],resolve)
        },
        {
          path:'/User',
          component:resolve=>require(['../pages/UserPage.vue'],resolve)
        },
        {
          path:'/Author',
          component:resolve=>require(['../pages/AuthorPage.vue'],resolve)
        },
        {
          path:'/BookList',
          component:resolve=>require(['../pages/BookListPage.vue'],resolve)
        },
        {
          path:'/Book',
          component:resolve=>require(['../pages/BookPage.vue'],resolve)
        },
        {
          path:'/ListBook',
          component:resolve=>require(['../pages/ListBookPage.vue'],resolve)
        },
        {
          path:'/Collect',
          component:resolve=>require(['../pages/CollectPage.vue'],resolve)
        },
        {
          path:'/BookListComment',
          component:resolve=>require(['../pages/BookListCommentPage.vue'],resolve)
        },
        {
          path:'/BookComment',
          component:resolve=>require(['../pages/BookCommentPage.vue'],resolve)
        },
      ]
    }
    
  ]
})
