import HttpRequest from './../http_request'

class ClientProvider extends HttpRequest {

    initiateCardReader(){
        return this.create('cardread');
    }

    getCardId(){
        return this.fetch('cardread');
    }

    validateClient(cardId) {
        return this.fetch('cards/' + cardId);
    }
}

export default ClientProvider
