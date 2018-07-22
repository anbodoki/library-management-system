import {ListResult} from "../model/response/list-result";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ResourceCopy} from "../model/resources/resourcecopy";
import {BaseService} from "./base.service";
import {Injectable} from "@angular/core";
import {ActionResponse} from "../model/response/action-response";

@Injectable()
export class ResourceCopyService {

  private url = 'atom/resource-api/';

  constructor(private baseService: BaseService<ResourceCopy>) {
  }

  findResourceCopies(request): Promise<ActionResponseWithData<ListResult<ResourceCopy>>> {
    return this.baseService.getAll(this.url + "get-resource-copies", request);
  }

  update(resource: ResourceCopy): Promise<ActionResponseWithData<ResourceCopy>> {
    return this.baseService.update(this.url + "update-resource-copy", resource);
  }

  create(resource: ResourceCopy): Promise<ActionResponseWithData<ResourceCopy>> {
    return this.baseService.update(this.url + "add-resource-copy", resource);
  }

  delete(id: number): Promise<ActionResponse> {
    return this.baseService.delete(this.url + "resource-copy", id);
  }
}
