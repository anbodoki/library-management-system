import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

import 'rxjs/add/operator/toPromise';
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {Utils} from "./utils";

declare let jquery: any;
declare let $: any;

@Injectable()
export class BaseService<T> {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private utils: Utils) {
  }

  private showLoader() {
    if (document.getElementById("loader")) document.getElementById("loader").classList.remove("hide");
  }

  public static hideLoader() {
    if (document.getElementById("loader")) document.getElementById("loader").classList.add("hide");
  }

  getAll(methodName, request): Promise<ActionResponseWithData<ListResult<T>>> {
    this.showLoader();
    let ref = this;
    return this.http.post(methodName, JSON.stringify(request), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponseWithData<ListResult<T>>;
      })
      .catch(this.handleError);
  }

  getSingle(methodName): Promise<ActionResponseWithData<T>> {
    this.showLoader();
    let ref = this;
    return this.http.get(methodName)
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponseWithData<T>;
      })
      .catch(this.handleError);
  }

  getAllByParam(methodName, param: string): Promise<ActionResponseWithData<ListResult<T>>> {
    this.showLoader();
    let ref = this;
    return this.http.get(methodName + '/' + param)
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponseWithData<ListResult<T>>;
      })
      .catch(this.handleError);
  }

  getAllByGet(methodName): Promise<ActionResponseWithData<any>> {
    this.showLoader();
    return this.http.get(methodName)
      .toPromise()
      .then(function (response) {
        BaseService.hideLoader();
        return response as ActionResponseWithData<any>;
      })
      .catch(this.handleError);
  }

  getAllByPost(methodName, request): Promise<ActionResponseWithData<any>> {
    this.showLoader();
    return this.http.post(methodName, JSON.stringify(request), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        BaseService.hideLoader();
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        return response as ActionResponseWithData<any>;
      })
      .catch(this.handleError);
  }

  getAllByParams(methodName, request): Promise<ActionResponseWithData<ListResult<T>>> {
    this.showLoader();
    let ref = this;
    return this.http.post(methodName, JSON.stringify(request), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponseWithData<ListResult<T>>;
      })
      .catch(this.handleError);
  }

  create(methodName, obj: T): Promise<ActionResponseWithData<T>> {
    this.showLoader();
    let ref = this;
    return this.http
      .post(methodName, JSON.stringify(obj), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponseWithData<T>;
      })
      .catch(this.handleError);
  }

  update(methodName, obj: T): Promise<ActionResponseWithData<T>> {
    if (this.utils.comparePrevAndCurrentObjects(obj)) return Promise.resolve({} as ActionResponseWithData<T>);
    this.showLoader();
    let ref = this;
    return this.http
      .post(methodName, JSON.stringify(obj), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponseWithData<T>;
      })
      .catch(this.handleError);
  }

  updateWithoutResponseData(methodName, obj): Promise<ActionResponse> {
    if (this.utils.comparePrevAndCurrentObjects(obj)) return Promise.resolve({} as ActionResponseWithData<T>);
    this.showLoader();
    let ref = this;
    return this.http
      .post(methodName, JSON.stringify(obj), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponse;
      })
      .catch(this.handleError);
  }

  delete(methodName, id: number): Promise<ActionResponse> {
    this.showLoader();
    let ref = this;
    const url = methodName + '/' + id;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponse;
      })
      .catch(this.handleError);
  }

  get(methodName): Promise<any> {
    let ref = this;
    return this.http.get(methodName)
      .toPromise()
      .then(function (response) {
        return response;
      })
      .catch(this.handleError);
  }

  post(methodName, obj): Promise<ActionResponse> {
    this.showLoader();
    let ref = this;
    return this.http
      .post(methodName, JSON.stringify(obj), {headers: this.headers})
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        BaseService.hideLoader();
        return response as ActionResponse;
      })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    if (error.status = 400) {
      BaseService.hideLoader();
      $("#alertModal .modal .modal-body p").html(error.statusText);
      $("#alertModal .modal").modal("show")
    }
    return null;
  }
}
