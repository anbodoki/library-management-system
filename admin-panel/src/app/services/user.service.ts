import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {User} from '../model/security/user';
import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {Router} from "@angular/router";

@Injectable()
export class UserService {

  private usersUrl = 'security/user-api/';

  constructor(private baseService: BaseService<User>,  private router: Router) {
  }

  findUsers(request): Promise<ActionResponseWithData<ListResult<User>>> {
    return this.baseService.getAll(this.usersUrl + "find", request);
  }

  findUsersQuick(request): Promise<ActionResponseWithData<ListResult<User>>> {
    return this.baseService.getAllByParams(this.usersUrl + "quick-find", request);
  }

  getUser(): Promise<ActionResponseWithData<User>> {
    return this.baseService.getSingle(this.usersUrl + "authorized-user");
  }

  create(user: User): Promise<ActionResponseWithData<User>> {
    return this.baseService.create(this.usersUrl + "save", user);
  }

  update(user: User): Promise<ActionResponseWithData<User>> {
    return this.baseService.update(this.usersUrl + "update", user);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.usersUrl + "user", id);
  }

  logout() {
    this.baseService.get("logout");
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
