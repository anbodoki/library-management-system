import {ActionResponse} from "./action-response";

export class ActionResponseWithData<T> extends ActionResponse {
  public data: T;
}
