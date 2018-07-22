<template>
    <div class="wrapper">
        <div class="chosen-book-container">
            <p>{{bookInfo}}</p>

            <div class="next-previous-buttons">
                <button class="cancel btn" @click="declineBook">Cancel</button>
                <button class="okay btn" @click="confirmBook">Ok</button>
            </div>
        </div>
    </div>

</template>

<script>
    export default {
        name: "ChosenBook",
        props: ['book-info'],
        methods: {
            confirmBook() {
                this.$emit('book-confirmed')
            },

            declineBook() {
                this.$emit('book-declined')
            }
        }
    }
</script>

<style scoped>

</style>