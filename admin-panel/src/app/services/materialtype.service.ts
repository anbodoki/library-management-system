import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {MaterialType} from "../model/resources/materialtype";

@Injectable()
export class MaterialTypeService {

  private url = 'atom/material-type-api/';

  constructor(private baseService: BaseService<MaterialType>) {
  }

  findMaterialTypes(request): Promise<ActionResponseWithData<ListResult<MaterialType>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findMaterialTypesQuick(request): Promise<ActionResponseWithData<ListResult<MaterialType>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  create(materialType: MaterialType): Promise<ActionResponseWithData<MaterialType>> {
    return this.baseService.create(this.url + "save", materialType);
  }

  update(materialType: MaterialType): Promise<ActionResponseWithData<MaterialType>> {
    return this.baseService.update(this.url + "update", materialType);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "materialType", id);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
