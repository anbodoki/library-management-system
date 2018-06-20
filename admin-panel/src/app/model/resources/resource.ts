import {Language} from "./language";
import {MaterialType} from "./materialtype";
import {Category} from "./category";

export class Resource {
  public id: number;
  public name: string;
  public author: string;
  public subname: string;
  public edition: string;
  public publisher: string;
  public editionDate: any;
  public language: Language;
  public pageNum: number;
  public isbn: string;
  public udc: string;
  public identifier: string;
  public resourceType: string;
  public materialType: MaterialType;
  public referenceURL: string;
  public creationDate: any;
  public modificationDate: any;
  public category: Category;
}
