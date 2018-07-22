<template>
    <div class="book-card">
        <div class="book-img cover-bg" :style="{ backgroundImage: 'url(' + book.imageUrl + ')' }"></div>
        <div class="book-title-and-author">
            <p class="book-title text-primary fs-22">{{book.title}}</p>
            <span class="book-author text-primary-light fs-16">By {{book.author}}</span>
        </div>
        <div class="book-actions"></div>
    </div>
</template>

<script>
    export default {
        props: ['book']
    }
</script>