<template>
    <div class="wrapper">
        <div class="phone">
            <div class="phone-container">
                <div class="left-container">
                    <p class="date-input-label">
                        Hello <b>{{clientName}}</b>, please enter book borrow days count
                    </p>
                    <input type="text" maxlength="11" v-model="dateCount"
                           class="number-input" id="numberInput" value=""
                           placeholder="Enter days"/>

                    <button class="submit-btn" type="submit" @click.prevent="submit">Submit</button>
                </div>

                <keyboard v-on:number-click="numberClick" v-on:delete-number="deleteNumber"></keyboard>
            </div>
        </div>
    </div>
</template>

<script>
    import Keyboard from './Keyboard'
    import moment from 'moment'


    export default {
        name: "DatePicker",
        props: ['client-name'],
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
                let date = new Date();
                date = date.setDate(date.getDate() + parseInt(this.dateCount));
                date = moment(date).format('DD/MM/YYYY HH:mm:ss');
                this.$emit('date-submitted', date)
            },
        },
        components: {
            Keyboard
        }
    }
</script>