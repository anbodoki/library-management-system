import {Privilege} from "./privilege";

export class Role {
  public id: number;
  public name: string;
  public color: string;
  public rolePrivileges: Privilege[] = [];
}
