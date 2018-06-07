<template>
    <div class="default-layout">
        <vue-header></vue-header>
        <side-nav></side-nav>
        <main>
            <router-view class="container content"></router-view>
        </main>
    </div>
</template>

<script>
    import VueHeader from '../layout/Header';
    import SideNav from '../layout/SideNav';

    export default {
        name: 'default-layout',
        components: {
            VueHeader,
            SideNav
        }
    }
</script>
