import {
  Component,
  ContentChildren,
  ElementRef,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  QueryList,
  SimpleChanges
} from "@angular/core";
import {LMSDynamicComboColumnComponent} from "./lms-dynamic-combo-column.component";

@Component({
  selector: 'lms-dynamic-component',
  host: {
    '(document:click)': 'handleClick($event)',
  },
  templateUrl: 'lms-dynamic-combo.component.html',
})

export class LMSDynamicComboComponent implements OnChanges {

  @Input() values: any[];
  @Input() selectedObject: any;
  @ContentChildren(LMSDynamicComboColumnComponent) columns: QueryList<LMSDynamicComboColumnComponent>;

  @Input() showValue: string;
  @Input() returnFieldName: string;
  @Input() label: string;
  @Input() fieldName: string;
  @Input() fieldObject: any;
  @Input() returnObject: boolean;
  @Input() searchable: boolean = true;
  @Input() placeholder: string;
  @Input() capRequired: boolean = false;
  @Input() alertText: string;

  @Output() onSearch = new EventEmitter();

  showAlert: boolean = false;

  value: string = '';
  inside: boolean = false;
  elementRef;

  constructor(myElement: ElementRef) {
    this.elementRef = myElement;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.fieldObject && this.fieldObject[this.fieldName]) {
      if (this.showValue) {
        this.value = this.selectedObject[this.showValue];
      } else {
        this.value = this.selectedObject;
      }
    }
    if (changes.selectedObject) {
      if (!this.selectedObject || (this.selectedObject && !this.selectedObject[this.showValue])) {
        this.value = null;
        this.showAlert = this.capRequired;
      } else {
        this.showAlert = false;
      }
    }
    // this code is commented because of error issues, when values list in empty values are hidden
    // if (changes.values && (!this.values || (this.values && this.values.length <= 0))) {
    //   this.value = null;
    //   this.value = '';
    //   this.showAlert = this.capRequired;
    // }
  }

  onSearched(term) {
    this.onSearch.emit(term);
    //same
    // if ((!this.values || (this.values && this.values.length <= 0))) {
    //   this.value = null;
    //   this.showAlert = this.capRequired;
    // }
  }

  chooseOption(item) {
    if (!this.returnObject) {
      this.fieldObject[this.fieldName] = item[this.returnFieldName];
    } else {
      this.fieldObject[this.fieldName] = item;
    }
    if (this.showValue) {
      this.value = item[this.showValue];
    } else if (item['name']) {
      this.value = item['name'];
    } else if (this.returnFieldName) {
      this.value = item[this.returnFieldName];
    } else {
      this.value = item;
    }

    this.inside = false;
  }

  handleClick(event) {
    let clickedComponent = event.target;
    this.inside = false;
    if (clickedComponent == document.querySelectorAll('lms-dynamic-component .field-remove i')[0]) return;
    do {
      if (clickedComponent === this.elementRef.nativeElement) {
        this.inside = true;
      }
      clickedComponent = clickedComponent.parentNode;
    } while (clickedComponent);
  }
}
