import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from "@angular/core";

@Component({
  selector: 'lms-tree-checkbox',
  templateUrl: 'lms-tree-checkbox.component.html',
})

export class LMSTreeCheckboxComponent implements OnChanges {

  @Input() items: {};
  @Input() viewName: string;
  @Input() targetObject: any;
  @Input() targetObjectField: string;
  @Input() identifier: any;
  @Input() label: string;

  keys: any[] = [];

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.items && this.items) {
      this.keys = Object.keys(this.items);
    }
  }

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
      if (arr[i][key] == value[key]) {
        return i;
      }
    }
    return -1;
  }

  allChildrenChecked(key) {
    let children = this.items[key];
    for (let i = 0; i < children.length; i++) {
      if (this.arrayContains(this.targetObject[this.targetObjectField], children[i], this.identifier) == -1) {
        return false;
      }
    }
    return true;
  }

  onParentChanged(key, checked) {
    let children = this.items[key];
    for (let i = 0; i < children.length; i++) {
      this.onChanged(children[i], checked);
    }
  }
}
