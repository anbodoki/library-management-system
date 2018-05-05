import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";
import {ConfigurationProperty} from "../../model/configuration/configurationProperty";
import {ConfigurationPropertyService} from "../../services/configuration-property.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'configuration-property-list',
  templateUrl: './configuration-property.component.html',
})

export class ConfigurationPropertyComponent implements OnInit {

  properties: ConfigurationProperty[];
  selectedProperty: ConfigurationProperty;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;
  configurationPropertyTypes: any[];

  constructor(private configurationPropertyService: ConfigurationPropertyService,
              private accessService: AccessService,
              private utils: Utils) {
    let ref = this;
    this.accessService.containsCode('configuration_property_manage').then(function (response) {
      ref.isManage = response;
    });
    this.configurationPropertyService.getTypeSizes().then(function (response) {
      ref.configurationPropertyTypes = response.data;
    });
  }

  public getProperties(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query :this.query, limit : limit, offset: offset};
    this.configurationPropertyService.findConfigurationPropertiesQuick(request).then(
      function (response) {
        ref.properties = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {

  }

  showAddEditModal(property): void {
    this.selectedProperty = <ConfigurationProperty> JSON.parse(JSON.stringify(property.row));
    this.utils.setPrevObj(JSON.stringify(property.row));
    this.utils.showContent("propertyDetails");
    $("#propertyModal").modal("show");
  }

  updateProperty() {
    if (!this.utils.formIsValid("#propertyModal")) {
      return;
    }
    let ref = this;
    this.configurationPropertyService.update(this.selectedProperty).then(function () {
      $("#propertyModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteProperty() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedProperty = new ConfigurationProperty();
    this.utils.showContent("propertyDetails");
    $("#propertyModal").modal("show");
  }

  saveProperty() {
    if (!this.utils.formIsValid("#propertyModal")) {
      return;
    }
    let ref = this;
    this.configurationPropertyService.create(this.selectedProperty).then(function (response) {
      if(response.success) {
        $("#propertyModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.configurationPropertyService.delete(this.selectedProperty.id).then(function (response) {
      if (response.success) {
        $("#propertyModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  reloadData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getProperties(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }
}
