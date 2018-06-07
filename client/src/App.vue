<template>
    <div id="app">
        <component v-if="layout" :is="layout"></component>
        <router-view></router-view>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex';
    import DefaultLayout from './components/layouts/defaultLayout';

    export default {
        name: 'App',

        computed: mapGetters(['layout']),

        methods: {
            ...mapActions(['setLayout']),
        },

        components: {
            DefaultLayout,
        },

        metaInfo: {
            title: 'Library',
        },
    }
</script>
