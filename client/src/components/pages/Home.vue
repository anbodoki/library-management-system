<template>
    <div class="home-page">
        <my-withdrawn-books></my-withdrawn-books>
        <my-favourite-books></my-favourite-books>
    </div>
</template>

<script>
    import MyWithdrawnBooks from '../partials/home/MyWithdrawnBooks';
    import MyFavouriteBooks from '../partials/home/MyFavouriteBooks';

    export default {
        components: {
            MyWithdrawnBooks,
            MyFavouriteBooks
        }
    }
</script>
