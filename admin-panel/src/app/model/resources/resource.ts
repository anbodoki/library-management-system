import {Language} from "./language";
import {MaterialType} from "./materialtype";
import {Category} from "./category";

export class Resource {
  public id: number;
  public name: string;
  public author: string;
  public subName: string;
  public edition: number;
  public publisher: string;
  public editionDate: any;
  public pageNum: number;
  public isbn: string;
  public udc: string;
  public referenceURL: string;
  public creationDate: any;
  public modificationDate: any;
  public resourceType: string;
  public materialType: MaterialType;
  public language: Language;
  public category: Category;
  public quantity: number;
  public rentedQuantity: number;
  public issn: string;
  public place: string;
  public imageUrl: string;
  public resourceUrl: string;
}
