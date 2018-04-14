export class ListResult<T> {
  public resultList: T[];
  public page: number;
  public limit: number;
  public offset: number;
  public count: number;
  public pageNum: number;
}
