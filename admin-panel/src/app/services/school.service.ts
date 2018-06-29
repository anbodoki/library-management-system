import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {School} from "../model/client/school";

@Injectable()
export class SchoolService {

  private url = 'client/school-api/';

  constructor(private baseService: BaseService<School>) {
  }

  findSchools(request): Promise<ActionResponseWithData<ListResult<School>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findSchoolsQuick(request): Promise<ActionResponseWithData<ListResult<School>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  create(school: School): Promise<ActionResponseWithData<School>> {
    return this.baseService.create(this.url + "save", school);
  }

  update(school: School): Promise<ActionResponseWithData<School>> {
    return this.baseService.update(this.url + "update", school);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "school", id);
  }

  getUniversities(): Promise<ActionResponseWithData<any>> {
    return this.baseService.getAllByGet(this.url + "universities");
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
