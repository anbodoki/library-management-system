import {Injectable} from "@angular/core";
import {DatePipe} from "@angular/common";

declare let jquery: any;
declare let $: any;

@Injectable()
export class Utils {

  datePickerConfig: {} = {format: "DD/MM/YYYY"};

  prevObj: string;

  public formIsValid(modalSelector) {
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

  public convertToIndexedArray(arr) {
    let map = [];
    for (let i = 0; i < arr.length; i++) {
      map[i] = {id: arr[i], name: arr[i]};
    }
    return map;
  }

  public setPrevObj(prevObj) {
    this.prevObj = prevObj;
  }

  public getPrevObj() {
    if (this.prevObj) return JSON.parse(this.prevObj);
    return null;
  }


  public comparePrevAndCurrentObjects(curr) {
    return this.prevObj == JSON.stringify(curr);
  }

  public arrayFieldsAsArray(arr, field) {
    let res = [];
    for (let i = 0; i < arr.length; i++) {
      res.push(arr[i][field]);
    }
    return res;
  }

  public formatDateArray(arr) {
    let res = [];
    let datePipe = new DatePipe("en-US");
    for (let i = 0; i < arr.length; i++) {
      res.push(datePipe.transform(arr[i], 'dd/MM/yyyy'));
    }
    return res;
  }

  public formatDateTimeArray(arr) {
    let res = [];
    let datePipe = new DatePipe("en-US");
    for (let i = 0; i < arr.length; i++) {
      res.push(datePipe.transform(arr[i], 'dd/MM/yyyy HH:mm'));
    }
    return res;
  }

  public getByIdentifier(arr, field, target) {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][field] == target) {
        return arr[i];
      }
    }
    return null;
  }

  public filterArrayByField(arr, field, target) {
    let res = [];
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][field] == target) {
        res.push(arr[i]);
      }
    }
    return res;
  }

  public filterArrayIdentifierByField(arr, field, identifier, target) {
    if (!arr || !target) {
      return;
    }
    let res = [];
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][field][identifier] == target[identifier]) {
        res.push(arr[i]);
      }
    }
    return res;
  }

  public filterArrayByFieldAndObject(arr, field, identifier, target, additionalFilterField, value) {
    if (!arr) return;
    let res = [];
    for (let i = 0; i < arr.length; i++) {
      if (value != null) {
        if (arr[i][field][identifier] == target[identifier] && arr[i][additionalFilterField] != value[additionalFilterField]) {
          res.push(arr[i]);
        }
      } else {
        if (arr[i][field][identifier] == target[identifier]) {
          res.push(arr[i]);
        }
      }
    }
    return res;
  }

  public showContent(tabId) {
    $("#" + tabId).trigger("click");
  }

  arrContains(arr, elem) {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] == elem) return true;
    }
    return false;
  }

  checkIfIsIncremental(arr, field) {
    let map = [];
    for (let i = 0; i < arr.length; i++) {
      if (this.arrContains(map, arr[i][field])) {
        return false;
      }
      map.push(arr[i][field]);
    }
    return true;
  }

  public showAlert(text) {
    $("#alertModal .modal .modal-body p").html(text);
    $("#alertModal .modal").modal("show")
  }
}
