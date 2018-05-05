import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';

import {BaseService} from "./base.service";
import {ActionResponse} from "../model/response/action-response";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {ConfigurationProperty} from "../model/configuration/configurationProperty";

@Injectable()
export class ConfigurationPropertyService {

  private configurationPropertyUrl = 'configuration/configuration-property-api/';

  constructor(private baseService: BaseService<ConfigurationProperty>) {
  }

  findConfigurationProperties(request): Promise<ActionResponseWithData<ListResult<ConfigurationProperty>>> {
    return this.baseService.getAll(this.configurationPropertyUrl + "find", request);
  }

  findConfigurationPropertiesQuick(request): Promise<ActionResponseWithData<ListResult<ConfigurationProperty>>> {
    return this.baseService.getAllByParams(this.configurationPropertyUrl + "quick-find", request);
  }

  create(ConfigurationProperty: ConfigurationProperty): Promise<ActionResponseWithData<ConfigurationProperty>> {
    return this.baseService.create(this.configurationPropertyUrl + "save", ConfigurationProperty);
  }

  update(ConfigurationProperty: ConfigurationProperty): Promise<ActionResponseWithData<ConfigurationProperty>> {
    return this.baseService.update(this.configurationPropertyUrl + "update", ConfigurationProperty);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.configurationPropertyUrl + "delete", id);
  }

  getTypeSizes(): Promise<ActionResponseWithData<any>> {
    return this.baseService.getAllByGet(this.configurationPropertyUrl + "configuration-property-types");
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
