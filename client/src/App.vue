<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';

    @Component
    export default class App extends Vue {
        appName = APP_NAME;
    }
</script>

<style lang="scss" scoped>
    .app-message {
        color: gray;
    }

    .nav-link {
        padding: 5px;
        color: gray;
        text-decoration: none;
    }

    .nav-link.router-link-active {
        font-weight: bold;
        color: blueviolet;
    }
</style>
