<template>
    <section class="my-favourite-books-section page-section">
        <div class="page-section-header">
            <h1 class="section-label text-primary-light f-nino-mtavruli">ჩემი ფავორიტი წიგნები</h1>
        </div>
        <div class="my-withdrawn-books">
            <books-slider :books="books"></books-slider>
        </div>
    </section>
</template>

<script>
    import BooksSlider from '../BooksSlider';
    import {mapGetters} from 'vuex';

    export default {
        computed: mapGetters(['books']),
        components: {
            BooksSlider
        }
    }
</script>