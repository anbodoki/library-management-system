import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {Client} from "../model/client/client";
import {Router} from "@angular/router";
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ListResult} from "../model/response/list-result";
import {ActionResponse} from "../model/response/action-response";
import {Mail} from "../model/client/mail";

@Injectable()
export class MailService {

  private url = 'atom/resource-borrow-api/';

  constructor(private baseService: BaseService<Mail>, private router: Router) {
  }

  sendMail(mail: Mail): Promise<ActionResponseWithData<Mail>> {
    return this.baseService.create(this.url + "send-mail", mail);
  }
}
