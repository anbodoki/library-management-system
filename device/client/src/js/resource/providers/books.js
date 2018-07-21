import HttpRequest from './../http_request'

class BooksProvider extends HttpRequest {

    getBookInfo(bookId) {
        return this.fetch('books/' + bookId);
    }
}

export default BooksProvider
