import {Injectable} from "@angular/core";

declare let jquery: any;
declare let $: any;

@Injectable()
export class AccessService {

  constructor() {

  }

  privileges: {};

  public setPrivileges(privileges) {
    this.privileges = privileges;
  }

  public getPrivileges() {
    return this.privileges;
  }

  containsGroupName(groupName) {
    return this.privileges && !!this.privileges[groupName];
  }

  formIsValid(modalSelector) {
    let res = true;
    $(modalSelector + ' input[required]').each(function () {
      if ($(this).hasClass('ng-invalid')) {
        res = false;
        return;
      }
    });
    $(modalSelector + ' select[required]').each(function () {
      if ($(this).hasClass('ng-invalid')) {
        res = false;
        return;
      }
    });
    return res;
  }

  containsCodeAfterInit(code) {
    let keys = Object.keys(this.privileges);
    for (let key in this.privileges) {
      let arr = this.privileges[key];
      for (let i = 0; i < arr.length; i++) {
        if (arr[i] == code) {
          return true;
        }
      }
    }
    return false;
  }
}
