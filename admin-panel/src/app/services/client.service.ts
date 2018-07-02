import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {Client} from "../model/client/client";
import {Router} from "@angular/router";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";

@Injectable()
export class ClientService {

  private clientsUrl = 'client/client-api/';

  constructor(private baseService: BaseService<Client>, private router: Router) {
  }

  findClients(request): Promise<ActionResponseWithData<ListResult<Client>>> {
    return this.baseService.getAll(this.clientsUrl + "find", request);
  }

  findClientsQuick(request): Promise<ActionResponseWithData<ListResult<Client>>> {
    return this.baseService.getAllByParams(this.clientsUrl + "quick-find", request);
  }
}
