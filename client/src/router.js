import Vue from 'vue';
import VueRouter from 'vue-router';
import makeRouter from './utils/router';

Vue.use(VueRouter);

const Home = () => import(/* webpackChunkName: "home" */ './components/pages/Home.vue');
const NotFound = () => import(/* webpackChunkName: "not-found" */ './components/pages/NotFound.vue');

const routes = [
    {path: '/', name: 'home', redirect: '/home'},
    {path: '/home', name: 'home', component: Home, meta: {title: 'Home'}},
    {path: '*', component: NotFound, meta: {title: 'Not Found'}},
];

const router = makeRouter(routes);

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    next();
});

export default router;
