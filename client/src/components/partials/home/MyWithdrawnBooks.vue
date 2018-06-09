<template>
    <section class="my-withdrawn-books-section">
        <h1 class="section-label text-primary-light f-nino-mtavruli">ჩემი წიგნები</h1>
        <div class="my-withdrawn-books">
            <books-slider :books="books"></books-slider>
        </div>
    </section>
</template>

<script>
    import BooksSlider from '../BooksSlider';

    export default {
        data() {
            return {
                books: [
                    {
                        title: 'User Experience Design',
                        author: 'Wouter de Bres',
                        imageUrl: '/dist/imgs/gandi.jpg'
                    },
                    {
                        title: 'User Experience Design',
                        author: 'Wouter de Bres',
                        imageUrl: '/dist/imgs/gandi.jpg'
                    }],
            }
        },
        components: {
            BooksSlider
        }
    }
</script>