
//import styles
import '../sass/main.scss';

//import html pages into bundle
import '../index.html';

//
import Vue from 'vue';

import Keyboard from './components/Keyboard.vue';
import ChosenBook from './components/ChosenBook.vue';

const app = new Vue({
    el: '#app',
    components: {
        Keyboard,
        ChosenBook
    }
});
