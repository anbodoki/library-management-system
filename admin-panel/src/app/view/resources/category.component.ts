import {Component, OnInit} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";
import {Category} from "../../model/resources/category";
import {CategoryService} from "../../services/category.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'categories-list',
  templateUrl: './category.component.html',
})

export class CategoryComponent implements OnInit {
  categories: Category[];
  selectedCategory: Category;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;

  constructor(private accessService: AccessService,
              private categoriesService: CategoryService,
              private utils: Utils) {
    let ref = this;
    this.accessService.containsCode('category_type_manage').then(function (response) {
      console.log(response);
      ref.isManage = response;
    });
  }

  public getCategories(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.categoriesService.findCategoriesQuick(request).then(
      function (response) {
        ref.categories = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
  }

  onSelect(category: Category): void {
    this.selectedCategory = category;
  }

  showAddEditModal(category): void {
    this.selectedCategory = <Category> JSON.parse(JSON.stringify(category.row));
    this.utils.setPrevObj(JSON.stringify(category.row));
    $("#categoryModal").modal("show");
  }

  updateCategory() {
    if (!this.utils.formIsValid('#categoryModal')) {
      return;
    }
    let ref = this;
    this.categoriesService.update(this.selectedCategory).then(function () {
      $("#categoryModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteCategory() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedCategory = new Category();
    $("#categoryModal").modal("show");
  }

  saveCategory() {
    if (!this.utils.formIsValid('#categoryModal')) {
      return;
    }
    let ref = this;
    this.categoriesService.create(this.selectedCategory).then(function () {
      $("#categoryModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.categoriesService.delete(this.selectedCategory.id).then(function (response) {
      if (response.success) {
        $("#categoryModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateCategoriesData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getCategories(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  filterClicked(request) {
    this.getFilteredCategories(request);
  }

  private getFilteredCategories(request) {
    let ref = this;
    this.categoriesService.findCategories(request).then(
      function (response) {
        ref.categories = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }
}
