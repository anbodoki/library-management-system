
//import styles
import '../sass/main.scss';

//import html pages into bundle
import '../index.html';

//
import Vue from 'vue';

import Keyboard from './components/Keyboard.vue';
import ChosenBook from './components/ChosenBook.vue';
import ResponseStatusMessage from "./components/ResponseStatusMessage";
import CardRead from './components/CardRead.vue';
import DatePicker from "./components/DatePicker";

const app = new Vue({
    el: '#app',
    data() {
        return {
            state: {
                main: 0,
                bookInfo: 1,
                card: 2,
                date: 3,
            },
            activeState: 2,
            responseStatusVisible: false,
            client: {
                bookId: '',
                cardId: '',
                date: null,
                name: ''
            },
            response: {
                message: '',
                status: ''
            }
        }
    },
    methods: {
        updateBookInfo(bookInfo) {
            console.log('book info updated', bookInfo);
            this.client.bookId = bookInfo;
            this.activeState = this.state.bookInfo;
        },

        bookConfirmed() {
            //open card screen
            this.activeState = this.state.card;
        },

        activateResponseStatusMessage(msg, status) {
            console.log('activate response msg', msg, status);
            this.response.message = msg;
            this.response.status = status;
            this.responseStatusVisible = true;
            setTimeout(() => {
                this.responseStatusVisible = false;
            }, 5000);
        },

        deactivateResponseStatusMessage() {
            this.response.message = '';
            this.response.status = '';
            this.responseStatusVisible = false;
        },

        backToMainScreen() {
            this.activeState = this.state.main;
        },

        bookSuccessfullyReturned(cardId, clientName) {
            this.client.name = clientName;
            this.client.cardId = cardId;
            this.submit();
        },

        submit() {
            this.activateResponseStatusMessage("Book successfully Returned", 'success');
            this.backToMainScreen();
        },

        clientSuccessfullyIdentified(cardId, clientName, action) {
            if (action === 'r') {
                this.bookSuccessfullyReturned();
            } else {
                this.client.name = clientName;
                this.client.cardId = cardId;
                this.activeState = this.state.date
            }
        },
    },
    components: {
        ResponseStatusMessage,
        ChosenBook,
        DatePicker,
        Keyboard,
        CardRead
    }
});
