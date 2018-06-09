<template>

    <div class="book-page">
        <div class="book-page-top">
            <div class="book-cover-image">
                <div class="book-cover" style="background-image: url('/dist/imgs/images/sherlock.jpg')"></div>
                <div class="download-book">
                    <img class="side-nav-menu-item-icon" src="/dist/imgs/icons/download.svg"/>
                    <span class="f-bgp-arial fs-14 text-primary">ჩამოტვირთე</span>
                </div>
            </div>

            <div class="book-info-container">
                <h1 class="fs-18 f-gotham-medium fc-black">Sherlock Holmes</h1>
                <p class="fs-14 f-gotham-light fc-black">by Artur Conan Doyle</p>
                <div class="book-info">
                    <p class="f-nino-mtavruli fs-14 text-primary-light">დარჩენილი: <span class="f-nino-mtavruli text-primary">4</span></p>
                    <p class="f-nino-mtavruli fs-14 text-primary-light">კატეგორია: <span class="f-nino-mtavruli text-primary">მხატვრული</span></p>
                    <p class="f-nino-mtavruli fs-14 text-primary-light">ენა: <span class="f-nino-mtavruli text-primary">ქართული</span></p>
                    <p class="f-nino-mtavruli fs-14 text-primary-light">მასალის სახე: <span class="f-nino-mtavruli text-primary">წიგნი</span></p>
                </div>
            </div>
        </div>

        <div class="book-page-bottom">

        </div>
    </div>
    
</template>

<script>
    export default {
        name: "book"
    }
</script>
