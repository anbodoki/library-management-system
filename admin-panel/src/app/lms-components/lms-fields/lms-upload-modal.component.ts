import {Component, EventEmitter, Input, Output} from "@angular/core";
import {UploadFileService} from "../../services/upload-file.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'lms-upload-modal',
  templateUrl: './lms-upload-modal.component.html'
})

export class LmsUploadModal {

  @Input() fieldObject: any;
  @Input() fieldName: string;
  @Input() prevFieldObject: any;
  @Input() abilityToUpload: boolean = true;

  @Output() uploadImageClick = new EventEmitter();

  constructor(private uploadService: UploadFileService){

  }

  closeModal() {
    if (this.fieldObject && this.prevFieldObject && this.fieldObject[this.fieldName] != this.prevFieldObject[this.fieldName]) {
      this.deleteFile(this.fieldObject[this.fieldName]);
      this.fieldObject[this.fieldName] = this.prevFieldObject[this.fieldName];
    }
    $(".uploadImageModal").modal("hide");
  }

  deleteFile(prevImage) {
    this.uploadService.deleteFile(prevImage);
  }

  saveImage() {
    $(".uploadImageModal").modal("hide");
    this.uploadImageClick.emit();
  }
}
