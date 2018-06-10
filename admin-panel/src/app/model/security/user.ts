import {Role} from "./role";

export class User {
  public id: number;
  public username: string;
  public firstName: string;
  public lastName: string;
  public email: string;
  public phone: string;
  public password: string;
  public active: boolean;
  public roles: Role[];
  public privileges: {};
  public imageUrl: string;
}
