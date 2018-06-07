import Router from 'vue-router';

/**
 * Create a router instance.
 *
 * @param  {Array} routes
 * @return {Router}
 */
export default function router(routes) {
    const router = new Router({
        routes,
        scrollBehavior,
        mode: 'history',
        saveScrollPosition: false,
        localized: true,
    });

    router.beforeEach((to, from, next) => {
        const components = router.getMatchedComponents({...to});

        if (components.length) {
            console.log(components, components[0].layout);
            setTimeout(() => {
                router.app.setLayout(components[0].layout || '');
            }, 0)
        }

        // if (!store.getters.authCheck && store.getters.authToken) {
        //     store.dispatch('fetchUser').then(() => next())
        // } else {
        //     next()
        // }
        next()
    });

    return router;
}

//
/**
 * @param  {Array} routes
 * @param  {Function} guard
 * @return {Array}
 */
function guard(routes, guard) {
    routes.forEach(route => {
        if (typeof route.beforeEnter === 'function') {
            route.beforeEnter = function (g1, g2, to, from, next) {
                g1(to, from, next);
                g2(to, from, next);
            }.bind(null, guard, route.beforeEnter);
        } else {
            route.beforeEnter = guard;
        }
    });

    window.routes = routes;

    return routes
}

/**
 * @param  {Route} to
 * @param  {Route} from
 * @param  {Object|undefined} savedPosition
 * @return {Object}
 */
function scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
        return savedPosition;
    }

    const position = {};

    if (to.hash) {
        position.selector = to.hash;
    }

    if (to.matched.some(m => m.meta.scrollToTop)) {
        position.x = 0;
        position.y = 0;
    }

    return position;
}
