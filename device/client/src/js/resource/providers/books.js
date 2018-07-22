import HttpRequest from './../http_request'

class BooksProvider extends HttpRequest {

    getBookInfo(bookId) {
        return this.fetch('books/' + bookId);
    }

    submit(cardId, bookId, date) {
        return this.create('booksubmissions', {card_id: cardId, book_id: bookId, date: date})
    }
}

export default BooksProvider
