import 'babel-polyfill';
import Vue from 'vue';
import { Component } from 'vue-property-decorator';
import { sync } from 'vuex-router-sync';

import './index.scss';
import store from './store/_index';
import App from './App.vue';
import router from './router';

Component.registerHooks(['beforeRouteEnter', 'beforeRouteLeave']);

sync(store, router);

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
});
