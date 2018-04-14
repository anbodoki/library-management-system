import {Component, Input} from "@angular/core";

@Component({
  selector: 'lms-multi-checkbox',
  templateUrl: 'lms-multi-checkbox.component.html',
})

export class LMSMultiCheckboxComponent{

  @Input() items: any[];
  @Input() label: string;
  @Input() viewName: string;
  @Input() targetObject: any;
  @Input() targetObjectField: string;
  @Input() identifier: any;
  @Input() small: boolean;
  @Input() imageField: string;

  onChanged(value, checked) {
    if (checked) {
      if (this.arrayContains(this.targetObject[this.targetObjectField], value, this.identifier) == -1) {
        this.targetObject[this.targetObjectField].push(value);
      }
    } else {
      if (this.arrayContains(this.targetObject[this.targetObjectField], value, this.identifier) > -1) {
        this.targetObject[this.targetObjectField].splice(this.arrayContains(this.targetObject[this.targetObjectField], value, this.identifier), 1);
      }
    }
  }

  private arrayContains(arr: any[], value, key) {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][key] == value[key] ) {
        return i;
      }
    }
    return -1;
  }

}
