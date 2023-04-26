<template>
  <div id="app">    
    <the-header/>
    <router-view class="music-content"/>
    <scroll-top/>
    <the-footer/>
  </div>
</template>

<script>
import ScrollTop from './components/ScrollTop.vue'
import TheHeader from './components/TheHeader.vue'
import TheFooter from './components/TheFooter.vue'
export default {
  name: 'App',
  components: {
    TheHeader,
    ScrollTop,
    TheFooter,
  },
  
}
</script>

<style  lang="scss" scoped>
@import './assets/css/app.scss';
</style>
