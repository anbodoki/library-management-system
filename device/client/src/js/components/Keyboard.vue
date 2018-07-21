<template>
    <!-- ## wrapper -->
    <div class="wrapper">
        <!-- ## phone area -->
        <div class="phone">
            <!-- ## phone area -->
            <div class="phone-container">
                <div class="left-container">
                    <input type="text" maxlength="11" v-model="bookId"
                           class="number-input" id="numberInput" value=""
                           placeholder="Enter Book ID"/>

                    <button class="submit-btn" type="submit" @click.prevent="submit()">Submit</button>
                </div>

                <!-- ## keyboard -->
                <div class="keyboard">
                    <div class="number">
                        <span v-for="number in 9" @click="numberClick(number)"><i>{{number}}</i></span>
                    </div>
                    <div class="number aling-right">
                        <span @click="numberClick(0)"><i>0</i></span>
                        <span><i class="delete" @click="deleteNumber()">
                            <img src="https://image.flaticon.com/icons/svg/61/61167.svg" width="30"
                                 height="30" alt="Left Arrow free icon"
                                 title="Left Arrow free icon"></i></span>
                    </div>
                </div>
            </div>
        </div>

    </div>

</template>

<script>
    import {BooksService} from "../resource";

    export default {
        name: "Keyboard",
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
                    console.log(res);
                });
            }
        }
    }


</script>

<style scoped>

</style>