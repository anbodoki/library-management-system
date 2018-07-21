<template>
    <div class="wrapper">
        <div class="chosen-book-container">
            <p>{{bookInfo}}</p>

            <div class="next-previous-buttons">
                <button class="cancel btn">Cancel</button>
                <button class="okay btn">Ok</button>
            </div>
        </div>
    </div>

</template>

<script>
    export default {
        name: "ChosenBook",
        props: ['bookInfo'],
        methods: {
            confirmBook() {
                this.$emit('bookConfirmed')
            },

            declineBook() {
                this.$emit('backToMainScreen')
            }
        }
    }
</script>

<style scoped>

</style>