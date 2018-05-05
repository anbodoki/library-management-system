import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {Language} from "../model/resources/language";

@Injectable()
export class LanguageService {

  private url = 'atom/language-api/';

  constructor(private baseService: BaseService<Language>) {
  }

  findLanguages(request): Promise<ActionResponseWithData<ListResult<Language>>> {
    return this.baseService.getAll(this.url + "find", request);
  }

  findLanguagesQuick(request): Promise<ActionResponseWithData<ListResult<Language>>> {
    return this.baseService.getAllByParams(this.url + "quick-find", request);
  }

  create(language: Language): Promise<ActionResponseWithData<Language>> {
    return this.baseService.create(this.url + "save", language);
  }

  update(language: Language): Promise<ActionResponseWithData<Language>> {
    return this.baseService.update(this.url + "update", language);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "language", id);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
