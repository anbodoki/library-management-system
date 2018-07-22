<template>
    <div class="keyboard">
        <div class="number">
            <span v-for="number in 9" @click="numberClick(number)"><i>{{number}}</i></span>
        </div>
        <div class="number aling-right">
            <span @click="numberClick(0)"><i>0</i></span>
            <span>
                <i class="delete" @click="deleteNumber()">
                    <img src="https://image.flaticon.com/icons/svg/61/61167.svg" width="30"
                         height="30" alt="Left Arrow free icon"
                         title="Left Arrow free icon">
                </i>
            </span>
        </div>
    </div>

</template>

<script>
    export default {
        name: "Keyboard",
        methods: {
            numberClick(number) {
                this.$emit('number-click', number)
            },
            deleteNumber() {
                this.$emit('delete-number')
            }
        }
    }
</script>