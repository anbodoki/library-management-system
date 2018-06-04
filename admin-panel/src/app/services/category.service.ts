import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {Category} from "../model/resources/category";

@Injectable()
export class CategoryService {

  private url = 'atom/category-api/';

  constructor(private baseService: BaseService<Category>) {
  }

  findCategories(request): Promise<ActionResponseWithData<ListResult<Category>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findCategoriesQuick(request): Promise<ActionResponseWithData<ListResult<Category>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  create(category: Category): Promise<ActionResponseWithData<Category>> {
    return this.baseService.create(this.url + "save", category);
  }

  update(category: Category): Promise<ActionResponseWithData<Category>> {
    return this.baseService.update(this.url + "update", category);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "category", id);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
