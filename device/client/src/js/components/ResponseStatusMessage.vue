<template>
    <div class="wrapper response-status-msg" :class="response.status">
        <div class="chosen-book-container">
            <p>{{response.message}}</p>

            <div class="next-previous-buttons">
                <button :class="response.status === 'error' ? 'cancel' : 'okay'" class="btn" @click="close">Close</button>
            </div>
        </div>
    </div>

</template>

<script>
    export default {
        name: "ResponseStatusMessage",
        props: ['response'],
        methods: {
            close() {
                this.$emit('close-response-status-msg');
            }
        }
    }
</script>

<style scoped>
    .response-status-msg {
        position: absolute;
        top: 0;
        left: 0;
        height: 100%;
    }

    .response-status-msg.error {

    }

    .response-status-msg.success {

    }
</style>