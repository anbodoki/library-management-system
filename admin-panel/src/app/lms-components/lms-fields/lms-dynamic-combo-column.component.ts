import {Component, ContentChild, Input} from '@angular/core';

@Component({
  selector: 'lms-dynamic-column',
  template: '',
})
export class LMSDynamicComboColumnComponent {

  @Input() field: string;
  @ContentChild('dynamicComboColumn') colTemplate;
}
