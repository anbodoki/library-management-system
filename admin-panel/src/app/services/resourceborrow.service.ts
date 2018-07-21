import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {MaterialType} from "../model/resources/materialtype";
import {ResourceBorrow} from "../model/resources/resourceborrow";
import {Language} from "../model/resources/language";
import {Mail} from "../model/client/mail";

@Injectable()
export class ResourceBorrowService {

  private url = 'atom/resource-borrow-api/';

  constructor(private baseService: BaseService<ResourceBorrow>) {
  }

  findResourceBorrows(request): Promise<ActionResponseWithData<ListResult<ResourceBorrow>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findResourceBorrowsQuick(request): Promise<ActionResponseWithData<ListResult<ResourceBorrow>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
