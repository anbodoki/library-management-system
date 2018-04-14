import {Component, Input} from "@angular/core";

@Component({
  selector: 'lms-combo',
  templateUrl: 'lms-combo.component.html',
})

export class LMSComboComponent {

  @Input() label: string;
  @Input() optionLabel: string;
  @Input() optionValue: any;
  @Input() values: any[];
  @Input() fieldName: string;
  @Input() fieldObject: any;
  @Input() removable: boolean;
  @Input() capRequired: boolean;
  @Input() alertText: string;
  @Input() visible: boolean;
  @Input() returnObject: boolean;
  @Input() placeholder: string;

  constructor() {
    this.visible = true;
    this.returnObject = true;
  }

  getValue(value) {

    for (let i = 0; i < this.values.length; i++) {
      if ('' + this.values[i][this.optionValue] == value) {
        if (this.returnObject) {
          this.fieldObject[this.fieldName] = this.values[i];
        } else {
          this.fieldObject[this.fieldName] = value;
        }
        return;
      }
    }
    this.fieldObject[this.fieldName] = null;
  }
}
