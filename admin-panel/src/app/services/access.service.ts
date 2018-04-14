import {Injectable} from "@angular/core";
import {UserService} from "./user.service";

declare let jquery: any;
declare let $: any;

@Injectable()
export class AccessService {

  constructor(private userService: UserService) {

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

  containsCode(code) {
    if (!this.privileges) {
      let ref = this;
      return this.userService.getUser().then(function (response) {
        ref.setPrivileges(response.data.privileges);
        return ref.containsCodeAfterInit(code);
      });
    } else {
      let ref = this;
      return Promise.resolve(ref.containsCodeAfterInit(code));
    }
  }

  containsCodes(codes: any[]) {
    if (!this.privileges) {
      let ref = this;
      return this.userService.getUser().then(function (response) {
        ref.setPrivileges(response.data.privileges);
        return ref.containsCodesAfterInit(codes);
      });
    } else {
      let ref = this;
      return Promise.resolve(ref.containsCodesAfterInit(codes));
    }
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

  containsCodesAfterInit(codes) {
    for(let i = 0; i < codes.length; i++) {
      if (!this.containsCodeAfterInit(codes[i])) {
        return false;
      }
    }
    return true;
  }
}
