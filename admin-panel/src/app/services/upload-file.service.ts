import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
import {ActionResponseWithData} from "../model/response/action-response-with-data";
import {ActionResponse} from "../model/response/action-response";

declare let jquery: any;
declare let $: any;

@Injectable()
export class UploadFileService {

  constructor(private http: HttpClient) {
  }

  private headers = new HttpHeaders({'Content-Type': 'application/json'});


  pushFileToStorage(file: File): Promise<ActionResponseWithData<string>> {
    let formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', 'filesystem/storage-api/store-file', formdata, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req)
      .toPromise()
      .then(function (response) {
        if (!response['body']['success']) {
          $("#alertModal .modal .modal-body p").html(response['body']['message']);
          $("#alertModal .modal").modal("show")
        }
        return response['body'] as ActionResponseWithData<string>;
      })
      .catch(this.handleError);
  }

  getFile(filename): Promise<ActionResponseWithData<any>> {
    return this.http.get('filesystem/storage-api/load-file/' + filename.split('.')[0] + '/' + filename.split('.')[1])
      .toPromise()
      .then(function (response) {
        if (!response['success']) {
          $("#alertModal .modal .modal-body p").html(response['message']);
          $("#alertModal .modal").modal("show")
        }
        return response as ActionResponseWithData<any>;
      })
      .catch(this.handleError);
  }

  deleteFile(filename): Promise<ActionResponse> {
    return this.http.post('filesystem/storage-api/delete-file', {fileUrl: filename}, {headers: this.headers})
      .toPromise()
      .then(function (response) {
        return response as ActionResponse;
      })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    $("#alertModal .modal .modal-body p").html(error.statusText);
    $("#alertModal .modal").modal("show");
    return null;
  }
}
