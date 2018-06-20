import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {User} from '../model/security/user';
import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";

@Injectable()
export class DashboardService {

  private dashboardUrl = 'application/initial-data/';

  constructor(private baseService: BaseService<User>) {
  }

  initialData(): Promise<ActionResponse> {
    return this.baseService.get(this.dashboardUrl + "initial-data");
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
