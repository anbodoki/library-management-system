import {
  Component,
  ContentChildren,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  QueryList,
  SimpleChanges
} from '@angular/core';
import {LMSColumnComponent} from "./lms-column.component";
import {PagingLoader} from "../../model/loader";
import {Utils} from "../../services/utils";

declare let jquery: any;
declare let $: any;
@Component({
  selector: 'lms-table',
  templateUrl: 'lms-table.component.html',
})
export class LMSTableComponent implements OnInit, OnChanges {

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.reloadEvent) {
      if(changes.reloadEvent.currentValue && changes.reloadEvent.currentValue.initialPage) {
        this.currentPage = 0;
      }
      this.reload();
    }
  }

  @Input() items: any[];
  @ContentChildren(LMSColumnComponent) columns: QueryList<LMSColumnComponent>;
  @Input() editable: boolean;
  @Input() addable: boolean;
  @Input() searchable: boolean;
  @Input() pagination: boolean;
  @Input() pageNum: number;
  @Input() reloadEvent: PagingLoader;
  @Input() advanceFilter: boolean;
  @Input() identifier: string;

  @Output() rowDoubleClick = new EventEmitter();
  @Output() rowClick = new EventEmitter();
  @Output() onAddButtonClick = new EventEmitter();
  @Output() onTermSearch = new EventEmitter();
  @Output() onPageChange = new EventEmitter();
  @Output() onFilteredClick = new EventEmitter();

  currentPage: number;
  pageNumOptions: any[];
  pageLimit: number;
  beingFiltered: boolean;
  filterRequest: {};
  prevIndex: number;

  ngOnInit(): void {
    this.items = [];
    this.currentPage = 0;
    this.reload();
    this.filterRequest = {};
  }

  constructor(private utils: Utils) {
    this.initPageNumOptions();
    this.pageLimit = this.pageNumOptions[0].value;
  }

  private initPageNumOptions() {
    this.pageNumOptions = [];
    this.pageNumOptions[0] = {id: 10, value: 10};
    this.pageNumOptions[1] = {id: 25, value: 25};
    this.pageNumOptions[2] = {id: 50, value: 50};
    this.pageNumOptions[3] = {id: 100, value: 100};
  }

  private rowDoubleClicked(row, event) {
    if (this.editable) {
      this.rowDoubleClick.emit({row, event});
    }
  }

  private rowClicked(row, index, event) {
    if (this.prevIndex != 0) {
      $('.row_' + this.prevIndex).css('background', 'none');
    }
    $('.row_' + index).css('background', '#dceaf5');
    this.prevIndex = index;
    this.rowClick.emit({row, event});
  }

  private onAddButtonClicked(event) {
    this.onAddButtonClick.emit({event});
  }

  private onTermSearched(searchedTerm, event) {
    this.onTermSearch.emit(searchedTerm);
    this.currentPage = 0;
    this.hideAdvanceSearch();
    this.reload();
  }

  private onPageChanged(requestedPage) {
    this.currentPage = requestedPage - 1;
    this.reload();
  }

  private getTrimmedArray(num) {
    if (this.pageNum && this.pageNum > num) {
      let arr = [];
      let end = this.currentPage + num;
      if (end > this.pageNum) {
        end = this.pageNum;
      }
      let start = end - this.currentPage > num ? this.currentPage : end - num;
      for (let i = start; i < end; i++) {
        arr[i - start] = i + 1;
      }
      return arr;
    } else {
      return Array(this.pageNum).fill(this.pageNum, 0, this.pageNum).map((e, i) => i + 1);
    }
  }

  onNextClicked() {
    if (!(this.currentPage == this.pageNum - 1 || this.pageNum == 0)) {
      this.currentPage++;
      this.reload();
    }
  }

  onPrevClicked() {
    if (!(this.currentPage == 0 || (this.pageNum == 0))) {
      this.currentPage--;
      this.reload();
    }
  }

  setNewPageNumOptions(value) {
    this.pageLimit = value;
    this.currentPage = 0;
    this.reload();
  }

  reload() {
    let page = this.currentPage;
    let limit = this.pageLimit;
    if (!this.beingFiltered) {
      this.onPageChange.emit({page, limit});
    } else {
      this.filterRequest['limit'] = this.pageLimit;
      this.filterRequest['offset'] = this.currentPage * this.pageLimit;
      this.onFilteredClick.emit(this.filterRequest);
    }
  }

  onFilteredClicked() {
    this.currentPage = 0;
    this.filterRequest['limit'] = this.pageLimit;
    this.filterRequest['offset'] = this.currentPage * this.pageLimit;
    this.onFilteredClick.emit(this.filterRequest);
  }

  onAdvanceButtonClicked() {
    let id = "";
    if (this.identifier)  id = "#" + this.identifier + " ";
    if ($(id + ".advance-filter").hasClass('hide')) {
      $(id + ".advance-filter").removeClass('hide');
      this.beingFiltered = true;
    } else {
      $(id + ".advance-filter").addClass('hide');
      this.clearFilterInputs();
      this.beingFiltered = false;
    }
  }

  hideAdvanceSearch() {
    this.beingFiltered = false;
    let id = "";
    if (this.identifier)  id = "#" + this.identifier + " ";
    if (!$(id + ".advance-filter").hasClass('hide')) {
      this.clearFilterInputs();
      $(id + ".advance-filter").addClass('hide');
    }
  }

  clearFilterInputs() {
    this.filterRequest = {};
  }

  capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

  keyDownFunction(event) {
    if(event.keyCode == 13) {
      this.onFilteredClicked();
    }
  }
}
