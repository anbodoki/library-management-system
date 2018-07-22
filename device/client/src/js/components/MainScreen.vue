<template>
    <div class="wrapper">
        <div class="phone">
            <div class="phone-container">
                <div class="left-container">
                    <input type="text" maxlength="11" v-model="bookId"
                           class="number-input" id="numberInput" value=""
                           placeholder="Enter Book ID"/>

                    <button class="submit-btn" type="submit" @click.prevent="submit()">Submit</button>
                </div>

                <keyboard v-on:number-click="numberClick" v-on:delete-number="deleteNumber"></keyboard>
            </div>
        </div>
    </div>
</template>

<script>
    import {BooksService} from "../resource";
    import Keyboard from "./Keyboard";

    export default {
        name: "MainScreen",
        data() {
            return {
                bookId: ""
            }
        },

        methods: {
            numberClick(number) {
                this.bookId += number;
            },
            deleteNumber() {
                this.bookId = this.bookId.slice(0, -1);
            },
            submit() {
                BooksService.getBookInfo(this.bookId).then(res => {
                    console.log('keyboard success', res);
                    this.$emit('book-info-updated', this.bookId, res.message, res.status);
                }).catch(err => {
                    console.log('keyboard error', err.response);
                    if (err.response.status == 404)
                        this.$emit('error', err.response.data.message, 'error');
                })
            }
        },
        components: {
            Keyboard
        }
    }


</script>

<style scoped>

</style>