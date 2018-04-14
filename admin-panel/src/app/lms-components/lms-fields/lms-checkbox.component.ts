import {Component, Input} from "@angular/core";

@Component({
  selector: "lms-checkbox",
  templateUrl: "lms-checkbox.component.html"
})

export class LMSCheckboxComponent {

  @Input() label: string;
  @Input() enabled: boolean;
  @Input() fieldName: string;
  @Input() fieldObject: any;
  @Input() visible: boolean = true;
  @Input() removable: boolean;
  @Input() capRequired: boolean;
  @Input() alertText: string;

}
