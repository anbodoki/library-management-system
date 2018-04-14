import {Component, Input} from "@angular/core";

@Component({
  selector: 'lms-input',
  templateUrl: 'lms-input.component.html',
})

export class LMSInputComponent {

  @Input() label: string;
  @Input() enabled: boolean;
  @Input() fieldName: string;
  @Input() fieldObject: any;
  @Input() fieldType: string;
  @Input() visible: boolean;
  @Input() placeholder: string;
  @Input() capPattern: string;
  @Input() capRequired: boolean;
  @Input() alertText: string;
  @Input() addon: string;
  constructor() {
    this.visible = true;
    this.enabled = true;
  }

}
