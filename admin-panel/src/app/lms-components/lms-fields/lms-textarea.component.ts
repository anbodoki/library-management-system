
import {Component, Input} from "@angular/core";

@Component({
  selector: "lms-textarea",
  templateUrl: "lms-textarea.component.html"
})

export class LMSTextareaComponent {

  @Input() label: string;
  @Input() enabled: boolean;
  @Input() fieldName: string;
  @Input() fieldObject: any;
  @Input() visible: boolean;
  @Input() capPattern: string;
  @Input() capRequired: boolean;
  @Input() alertText: string;
  @Input() placeholder: string;

  constructor() {
    this.visible = true;
    this.enabled = true;
  }

}
