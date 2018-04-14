import {Component, ContentChild, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'lms-column',
  template: '',
})
export class LMSColumnComponent {

  @Input() header: string;
  @Input() field: string;
  @Input() filtered: string;
  @Input() filteredOptions: any[];
  @Input() filteredFieldName: string;
  @Input() filteredOptionName: string;
  @Input() filteredOptionValue: string;
  @Input() filteredOptionReturnObject: boolean;
  @Input() filteredOptionColumns: any[];
  @Input() hidden: boolean;
  @Input() incremental: boolean = false;
  @Output() onSearchFunction = new EventEmitter();
  @ContentChild('dataTableCell') cellTemplate;

  public onSearchedFunction(bla) {
    this.onSearchFunction.emit(bla);
  }

}
