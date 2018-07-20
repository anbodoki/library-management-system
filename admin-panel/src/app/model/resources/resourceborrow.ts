import {ResourceCopy} from "./resourcecopy";

export class ResourceBorrow {

  public id: number;
  public resourceCopy: ResourceCopy;
  public clientId: number;
  public borrowTime: any;
  public returnTime: any;
  public scheduledReturnTime: any;
  public critical: boolean;
}
