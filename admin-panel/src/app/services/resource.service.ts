import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {Resource} from "../model/resources/resource";

@Injectable()
export class ResourceService {

  private url = 'atom/resource-api/';

  constructor(private baseService: BaseService<Resource>) {
  }

  findResources(request): Promise<ActionResponseWithData<ListResult<Resource>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findResourcesQuick(request): Promise<ActionResponseWithData<ListResult<Resource>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  create(resource: Resource): Promise<ActionResponseWithData<Resource>> {
    return this.baseService.create(this.url + "save", resource);
  }

  update(resource: Resource): Promise<ActionResponseWithData<Resource>> {
    return this.baseService.update(this.url + "update", resource);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "resource", id);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
