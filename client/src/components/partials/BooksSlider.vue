<template>
    <div class="books-slider books-grid-container">
        <template v-for="book in books">
            <book-preview :book="book"></book-preview>
        </template>
    </div>
</template>

<script>
    import BookPreview from './BookPreview';
    import Slick from 'vue-slick';

    export default {
        props: ['books'],
        components: {
            BookPreview,
            Slick
        }
    }
</script>