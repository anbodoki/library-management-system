<template>
    <div class="wrapper">
        <div class="phone">
            <div class="phone-container">
                <div class="left-container">
                    <input type="text" maxlength="11" v-model="dateCount"
                           class="number-input" id="numberInput" value=""
                           placeholder="Enter days"/>

                    <button class="submit-btn" type="submit" @click.prevent="submit()">Submit</button>
                </div>

                <keyboard v-on:number-click="numberClick" v-on:delete-number="deleteNumber"></keyboard>
            </div>
        </div>
    </div>
</template>

<script>
    import Keyboard from './Keyboard'

    export default {
        name: "DatePicker",
        data() {
            return {
                dateCount: ""
            }
        },

        methods: {
            numberClick(number) {
                this.dateCount += number;
            },
            deleteNumber() {
                this.dateCount = this.dateCount.slice(0, -1);
            },
            submit() {
            }
        },
        components: {
            Keyboard
        }
    }
</script>