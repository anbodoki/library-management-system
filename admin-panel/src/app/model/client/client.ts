import {School} from "./school";

export class Client {
  public id: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public phone: string;
  public imageUrl: string;
  public active: boolean;
  public school: School;
}
