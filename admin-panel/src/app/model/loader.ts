export class PagingLoader {

  private change: boolean;
  initialPage: boolean;

  constructor(change: boolean, initialPage: boolean) {
    this.change = change;
    this.initialPage = initialPage;
  }

  load(initialPage) {
    this.change = !this.change;
    this.initialPage = initialPage;
    return new PagingLoader(this.change, this.initialPage);
  }
}
