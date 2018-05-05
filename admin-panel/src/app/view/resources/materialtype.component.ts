import {Component, OnInit} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";
import {MaterialType} from "../../model/resources/materialtype";
import {MaterialTypeService} from "../../services/materialtype.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'material-types-list',
  templateUrl: './materialtype.component.html',
})

export class MaterialTypeComponent implements OnInit {
  materialTypes: MaterialType[];
  selectedMaterialType: MaterialType;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;

  constructor(private accessService: AccessService,
              private materialTypeService: MaterialTypeService,
              private utils: Utils) {
    let ref = this;
    this.accessService.containsCode('material_type_manage').then(function (response) {
      console.log(response);
      ref.isManage = response;
    });
  }

  public getMaterialTypes(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.materialTypeService.findMaterialTypesQuick(request).then(
      function (response) {
        ref.materialTypes = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
  }

  onSelect(materialType: MaterialType): void {
    this.selectedMaterialType = materialType;
  }

  showAddEditModal(materialType): void {
    this.selectedMaterialType = <MaterialType> JSON.parse(JSON.stringify(materialType.row));
    this.utils.setPrevObj(JSON.stringify(materialType.row));
    $("#materialTypeModal").modal("show");
  }

  updateMaterialType() {
    if (!this.utils.formIsValid('#materialTypeModal')) {
      return;
    }
    let ref = this;
    this.materialTypeService.update(this.selectedMaterialType).then(function () {
      $("#materialTypeModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteMaterialType() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedMaterialType = new MaterialType();
    $("#materialTypeModal").modal("show");
  }

  saveMaterialType() {
    if (!this.utils.formIsValid('#materialTypeModal')) {
      return;
    }
    let ref = this;
    this.materialTypeService.create(this.selectedMaterialType).then(function () {
      $("#materialTypeModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.materialTypeService.delete(this.selectedMaterialType.id).then(function (response) {
      if (response.success) {
        $("#materialTypeModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateMaterialTypesData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getMaterialTypes(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  private getFilteredMaterialTypes(request) {
    let ref = this;
    this.materialTypeService.findMaterialTypes(request).then(
      function (response) {
        ref.materialTypes = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }
}
