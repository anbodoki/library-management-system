import {Component, Input, OnInit} from '@angular/core';
import {UploadFileService} from "../../services/upload-file.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'lms-upload',
  templateUrl: 'lms-upload.component.html'
})
export class FormUploadComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;

  @Input() fieldObject: any;
  @Input() fieldName: string;
  @Input() prevFieldObject: any;

  constructor(private uploadService: UploadFileService) {
  }

  ngOnInit() {
  }

  selectFile(event) {
    const file = event.target.files.item(0);

    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      alert('invalid format!');
    }
  }

  // onCancelClicked() {
  //   if (this.fieldObject && this.prevFieldObject && this.fieldObject[this.fieldName] != this.prevFieldObject[this.fieldName]) {
  //     this.deleteFile(this.fieldObject[this.fieldName]);
  //   }
  // }

  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    let ref = this;
    let prevImage;
    if (this.fieldObject[this.fieldName]) {
      prevImage = this.fieldObject[this.fieldName];
    }
    this.uploadService.pushFileToStorage(this.currentFileUpload).then(function(response) {
      if (response.success) {
        ref.showFile(response.data);
        if (prevImage) {
          if (ref.prevFieldObject && ref.prevFieldObject[ref.fieldName] && ref.prevFieldObject[ref.fieldName] != prevImage) {
            ref.deleteFile(prevImage);
          }
        }
      }
    });
    this.selectedFiles = undefined
  }

  private showFile(filename) {
    let ref = this;
    this.uploadService.getFile(filename).then(function (response) {
      if (response.success) {
        ref.fieldObject[ref.fieldName] = response.data['fileUrl'];
      }
    });
  }

  deleteFile(prevImage) {
    this.uploadService.deleteFile(prevImage);
  }
}
