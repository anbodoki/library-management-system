import {Component, Input} from "@angular/core";
import {selector} from "rxjs/operator/publish";

@Component({
  selector: 'lms-color-picker',
  templateUrl: 'lms-color-picker.component.html'
})

export class LMSColorPickerComponent {

  @Input() targetObject: any;
  @Input() targetObjectField: string;
  @Input() label: string;

}
