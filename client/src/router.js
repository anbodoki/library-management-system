import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const Home = () => import(/* webpackChunkName: "home" */ './components/pages/Home.vue');
const NotFound = () => import(/* webpackChunkName: "not-found" */ './components/pages/NotFound.vue');

const routes = [
    {
        path: '/', component: require('./components/layouts/defaultLayout.vue'), children: [
            {path: '/', name: 'home', redirect: '/home'},
            {path: '/home', name: 'home', component: Home, meta: {title: 'Home'}},
        ]
    },
    {path: '*', component: NotFound, meta: {title: 'Not Found'}},
];

const router = new VueRouter({routes, mode: 'history'});

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    next();
});

export default router;
