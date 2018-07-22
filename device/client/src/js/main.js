
//import styles
import '../sass/main.scss';

//import html pages into bundle
import '../index.html';

//
import Vue from 'vue';

import Keyboard from './components/Keyboard.vue';
import ChosenBook from './components/ChosenBook.vue';
import CardRead from './components/CardRead.vue';

const app = new Vue({
    el: '#app',
    data() {
        return {
            state: {
                main: 0,
                bookInfo: 1,
                card: 2,
                date: 1,
            },
            activeState: 0,
            responseStatusVisible: false,
            response: {
                responseMsg: '',
                status: ''
            }
        }
    },
    methods: {
        updateBookInfo(bookInfo) {
            this.bookInfo = bookInfo;
            this.activeState = this.state.bookInfo;
        },

        bookConfirmed() {
            //open card screen
            this.activeState = this.state.card;
        },

        activateResponseStatusMessage(msg, status) {
            console.log('activate response msg', msg, status);
            this.response.responseMsg = msg;
            this.response.status = status;
            this.responseStatusVisible = true;
            setTimeout(() => {
                this.responseStatusVisible = false;
            }, 5000);
        },

        deactivateResponseStatusMessage() {
            this.response.responseMsg = '';
            this.response.status = '';
            this.responseStatusVisible = false;
        },

        backToMainScreen() {
            this.activeState = this.state.main;
        }
    },
    components: {
        Keyboard,
        ChosenBook,
        CardRead
    }
});
