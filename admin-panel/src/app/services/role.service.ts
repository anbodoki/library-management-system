import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {Role} from '../model/security/role';
import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";

@Injectable()
export class RoleService {

  private url = 'security/role-api/';

  constructor(private baseService: BaseService<Role>) {
  }

  findRoles(request): Promise<ActionResponseWithData<ListResult<Role>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findRolesQuick(request): Promise<ActionResponseWithData<ListResult<Role>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  create(role: Role): Promise<ActionResponseWithData<Role>> {
    return this.baseService.create(this.url + "save", role);
  }

  update(role: Role): Promise<ActionResponseWithData<Role>> {
    return this.baseService.update(this.url + "update", role);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "delete", id);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
