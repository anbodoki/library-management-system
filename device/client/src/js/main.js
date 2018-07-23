//import styles
import '../sass/main.scss';

//import html pages into bundle
import '../index.html';

//
import Vue from 'vue';

import MainScreen from "./components/MainScreen";
import ChosenBook from './components/ChosenBook.vue';
import ResponseStatusMessage from "./components/ResponseStatusMessage";
import CardRead from './components/CardRead.vue';
import DatePicker from "./components/DatePicker";
import {BooksService} from "./resource";

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
            activeState: 0,
            responseStatusVisible: false,
            client: {
                bookId: '',
                bookName: '',
                cardId: '',
                date: null,
                name: '',
                action: 'returned'
            },
            response: {
                message: '',
                status: ''
            }
        }
    },
    computed: {
        lastStep() {
            return (this.activeState == this.state.date ||  this.activeState == this.state.card)
        }
    },
    methods: {
        updateBookInfo(bookId, bookName, action) {
            this.client.bookId = bookId;
            this.client.bookName = bookName;
            console.log('action? ', action)
            this.client.action = action;
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
            this.response.status = '';
            this.response.message = '';
            this.responseStatusVisible = false;
            if (this.lastStep)
                this.clearAll();
        },

        backToMainScreen() {
            this.activeState = this.state.main;
        },

        submit() {
            BooksService.submit(this.client.cardId, this.client.bookId, this.client.date).then(res => {
                this.activateResponseStatusMessage("Book successfully " + this.client.action, 'success');
            }).catch(err => {
                this.activateResponseStatusMessage("Couldn't make action", 'error');
            });
            this.backToMainScreen();
        },

        clientSuccessfullyIdentified(cardId, clientName) {
            this.client.name = clientName;
            this.client.cardId = cardId;
            console.log('action', this.client.action);
            if (this.client.action === 'returned') {
                this.submit();
            } else {
                this.activeState = this.state.date
            }
        },

        dateSubmitted(date) {
            this.client.date = date;
            this.submit()
        },

        clearAll() {
            this.activeState = 0;
            this.client = {
                bookId: '',
                bookName: '',
                cardId: '',
                date: null,
                name: '',
                action: 'returned'
            };
            this.response = {
                message: '',
                status: ''
            }
        }
    },
    components: {
        ResponseStatusMessage,
        MainScreen,
        ChosenBook,
        DatePicker,
        CardRead
    }
});
