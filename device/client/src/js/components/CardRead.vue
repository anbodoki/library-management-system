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
                            console.log("shemodixar aq???", res);
                            clearInterval(this.getCardTimer);
                            this.validateClient(res.card_id);
                        }).catch((err) => {
                            // this.$emit('error', 'Card not identified' , 'error');
                        });
                    }, 1000);
                });
            },

            validateClient(cardId) {
                ClientService.validateClient(cardId).then(clientInfo => {
                    console.log('Validate client success', clientInfo.client_name);
                    this.$emit('client-successfully-identified', cardId, clientInfo.client_name);
                }).catch(err => {
                    console.log('Validate client error', err);
                    this.$emit('error', 'Client not identified', 'error');
                    this.initiateCardReader();
                });
            },

            cancelClicked() {
                clearInterval(this.getCardTimer);
                this.$emit('cancel-clicked')
            },

            beforeDestroy() {
                clearInterval(this.getCardTimer);
            },
        }
    }
</script>

<style scoped>

</style>