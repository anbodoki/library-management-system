<template>
    <div class="wrapper">

        <div class="chosen-book-container">
            <div class="place-card-container">
                <div class="arrow-left"></div>
                <p>Please place your card</p>
            </div>

            <div class="next-previous-buttons">
                <button class="cancel btn" @click="cancelClicked">Cancel</button>
            </div>
        </div>

    </div>
</template>

<script>
    import {ClientService} from "../resource";

    export default {
        name: "CardRead",

        mounted() {
            this.initiateCardReader();
        },

        data() {
            return {
                getCardTimer: null
            }
        },

        methods: {

            initiateCardReader() {
                ClientService.initiateCardReader().then(() => {
                    this.getCardTimer = setInterval(() => {
                        ClientService.getCardId().then( res => {
                            this.validateClient(res);
                            clearInterval(this.getCardTimer);
                        }).catch((err) => {
                            this.$emit('error', err, 'error');
                        });
                    }, 1000);
                });
            },

            validateClient(cardId) {
                ClientService.validateClient(cardId).then(clientName => {
                    console.log('Validate client success', clientName);
                    this.$emit('client-successfully-identified', cardId, clientName);
                }).catch(err => {
                    console.log('Validate client error', err);
                    this.$emit('error', err, 'error');
                    this.initiateCardReader();
                });
            },

            cancelClicked() {
                clearInterval(this.getCardTimer);
            },

            beforeDestroy() {
                clearInterval(this.getCardTimer);
            },

            success() {
            }
        }
    }
</script>

<style scoped>

</style>