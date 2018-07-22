import {Component, ContentChildren, Input, OnChanges, QueryList, SimpleChanges} from "@angular/core";
import {LMSColumnComponent} from "../lms-table/lms-column.component";

@Component({
  selector: "lms-inline",
  templateUrl: "./lms-inline-editing.component.html"
})

export class LMSInlineEditingComponent implements OnChanges {

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.fieldObject) {
      this.items = this.fieldObject[this.fieldName];
    }
  }

  @Input() items: any[];
  @Input() addable: boolean;
  @Input() fieldObject: any;
  @Input() fieldName: string;

  @ContentChildren(LMSColumnComponent) columns: QueryList<LMSColumnComponent>;

  onAddClicked() {
    let obj = {};
    for (let i = 0; i < this.columns['_results'].length; i++) {
      if (!this.columns['_results'][i]) continue;
      if (this.columns['_results'][i] && this.columns['_results'][i]['incremental']) {
        obj[this.columns['_results'][i]['field']] = this.items.length + 1;
      } else {
        obj[this.columns['_results'][i]['field']] = null;
      }
    }
    this.items.push(obj);
  }

  onRemoveClicked(index) {
    console.log(index);
    this.items.splice(index, 1);
  }

}
