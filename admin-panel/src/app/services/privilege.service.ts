import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';
import {BaseService} from "./base.service";
import {ActionResponseWithData} from "../model/response/action-response-with-data";

@Injectable()
export class PrivilegeService {

  private url = 'security/role-api/';

  constructor(private baseService: BaseService<any>) {
  }

  findPrivileges(): Promise<ActionResponseWithData<any>> {
    return this.baseService.getAllByGet(this.url + "privileges");
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
