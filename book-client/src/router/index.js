import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/Home'
import My from '@/pages/My'
import Author from '@/pages/Author'
import BookList from '@/pages/BookList'
import Search from '@/pages/Search'
import Lyric from '@/pages/Lyric'
import SignUp from '@/pages/SignUp'
import LoginIn from '@/pages/LoginIn'
import Setting from '@/pages/Setting'
import AuthorAlbum from '@/pages/AuthorAlbum'
import BookListAlbum from '@/pages/BookListAlbum'
import BookAlbum from '@/pages/BookAlbum'
import Publish from '@/pages/publish'
Vue.use(Router)

export default new Router({
  routes: [    
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path:'/my',
      name:'my',
      component:My
    },
    {
      path:'/author',
      name:'author',
      component:Author
    },
    {
      path:'/book-list',
      name:'book-list',
      component:BookList
    },
    {
      path:'/search',
      name:'search',
      component:Search
    },
    {
      path:'/lyric',
      name:'lyric',
      component:Lyric
    },
    {
      path:'/login-in',
      name:'login-in',
      component:LoginIn
    },
    {
      path:'/sign-up',
      name:'sign-up',
      component:SignUp
    },
    {
      path:'/setting',
      name:'setting',
      component:Setting
    },
    {
      path:'/author-album/:id',
      name:'author-album',
      component:AuthorAlbum
    },
    {
      path:'/book-list-album/:id',
      name:'book-list-album',
      component:BookListAlbum
    },
    {
      path:'/book-album/:id',
      name:'book-album',
      component:BookAlbum
    },
    {
      path:'/publish',
      name:'publish',
      component:Publish
    },
  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
})
