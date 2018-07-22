<template>
    <div class="wrapper">
        <div class="place-card-container">
            <div class="arrow-left"></div>
            <p>Please place your card</p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "CardRead",
        data() {
            return {
                cardId: '',
                clientName: '',
                action: ''
            }
        },
        methods: {
            success() {
                this.$emit('client-successfully-identified', this.cardId, this.clientName, this.action);
            }
        }
    }
</script>

<style scoped>

</style>