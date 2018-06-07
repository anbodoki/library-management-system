import 'babel-polyfill';
import Vue from 'vue';
import {sync} from 'vuex-router-sync';

import './index.scss';
import store from './store/_index';
import App from './App.vue';
import router from './router';

sync(store, router);

new Vue({
    el: '#app',
    store,
    router,
    ...App
}).$mount('#app');
