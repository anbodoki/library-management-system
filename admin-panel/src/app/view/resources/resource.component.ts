import {Component, OnInit} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";
import {Resource} from "../../model/resources/resource";
import {ResourceService} from "../../services/resource.service";
import {Language} from "../../model/resources/language";
import {MaterialType} from "../../model/resources/materialtype";
import {Category} from "../../model/resources/category";
import {LanguageService} from "../../services/language.service";
import {MaterialTypeService} from "../../services/materialtype.service";
import {CategoryService} from "../../services/category.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'resource-list',
  templateUrl: './resource.component.html',
})

export class ResourceComponent implements OnInit {
  resources: Resource[];
  selectedResource: Resource;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;
  languages: Language[];
  resourceTypes: any[];
  materialTypes: MaterialType[];
  categories: Category[];


  constructor(private accessService: AccessService,
              private resourceService: ResourceService,
              private languagesService: LanguageService,
              private materialTypesService: MaterialTypeService,
              private categoryService: CategoryService,
              private utils: Utils) {
    let ref = this;
    this.accessService.containsCode('resource_manage').then(function (response) {
      ref.isManage = response;
    });
    this.initLanguages("");
    this.initMaterialTypes("");
    this.initResourceTypes();
    this.initCategories("");
  }

  initLanguages(query) {
    let ref = this;
    this.languagesService.findLanguages({query: query, limit: 10, offset: 0}).then(function (response) {
      if (response.success) {
        ref.languages = response.data.resultList;
      }
    });
  }

  initMaterialTypes(query) {
    let ref = this;
    this.materialTypesService.findMaterialTypes({query: query, limit: 10, offset: 0}).then(function (response) {
      if (response.success) {
        ref.materialTypes = response.data.resultList;
      }
    });
  }

  initResourceTypes() {
    let ref = this;
    this.resourceService.getResourceTypes().then(function (response) {
      ref.resourceTypes = response.data;
    });
  }

  initCategories(query) {
    let ref = this;
    this.categoryService.findCategories({query: query, limit: 10, offset: 0}).then(function (response) {
      if (response.success) {
        ref.categories = response.data.resultList;
      }
    });
  }

  public getResources(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.resourceService.findResourcesQuick(request).then(
      function (response) {
        ref.resources = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
  }

  onSelect(resource: Resource): void {
    this.selectedResource = resource;
  }

  showAddEditModal(resource): void {
    this.initLanguages("");
    this.initMaterialTypes("");
    this.initResourceTypes();
    this.initCategories("");
    this.selectedResource = <Resource> JSON.parse(JSON.stringify(resource.row));
    this.utils.setPrevObj(JSON.stringify(resource.row));
    $("#resourceModal").modal("show");
  }

  updateResource() {
    if (!this.utils.formIsValid('#resourceModal')) {
      return;
    }
    let ref = this;
    this.resourceService.update(this.selectedResource).then(function () {
      $("#resourceModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteResource() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.initLanguages("");
    this.initMaterialTypes("");
    this.initResourceTypes();
    this.initCategories("");
    this.selectedResource = new Resource();
    $("#resourceModal").modal("show");
  }

  saveResource() {
    if (!this.utils.formIsValid('#resourceModal')) {
      return;
    }
    let ref = this;
    this.resourceService.create(this.selectedResource).then(function () {
      $("#resourceModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.resourceService.delete(this.selectedResource.id).then(function (response) {
      if (response.success) {
        $("#resourceModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateResourcesData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getResources(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  filterClicked(request) {
    this.getFilteredResources(request);
  }

  private getFilteredResources(request) {
    let ref = this;
    this.resourceService.findResources(request).then(
      function (response) {
        ref.resources = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }
}
