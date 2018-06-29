import {Component, OnInit} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";
import {School} from "../../model/client/school";
import {SchoolService} from "../../services/school.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'school-list',
  templateUrl: './school.component.html',
})

export class SchoolComponent implements OnInit {
  schools: School[];
  universities: any[];
  selectedSchool: School;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;

  constructor(private accessService: AccessService,
              private schoolService: SchoolService,
              private utils: Utils) {
    let ref =this;
    this.accessService.containsCode('school_manage').then(function (response) {
      ref.isManage = response;
    });
    this.initUniversities()
  }

  public getSchools(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.schoolService.findSchoolsQuick(request).then(
      function (response) {
        ref.schools = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
  }

  onSelect(school: School): void {
    this.selectedSchool = school;
  }

  showAddEditModal(school): void {
    this.selectedSchool = <School> JSON.parse(JSON.stringify(school.row));
    console.log(this.selectedSchool);
    this.utils.setPrevObj(JSON.stringify(school.row));
    $("#schoolModal").modal("show");
  }

  updateSchool() {
    if (!this.utils.formIsValid('#schoolModal')) {
      return;
    }
    let ref = this;
    this.schoolService.update(this.selectedSchool).then(function () {
      $("#schoolModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteSchool() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedSchool = new School();
    $("#schoolModal").modal("show");
  }

  saveSchool() {
    if (!this.utils.formIsValid('#schoolModal')) {
      return;
    }
    let ref = this;
    this.schoolService.create(this.selectedSchool).then(function () {
      $("#schoolModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.schoolService.delete(this.selectedSchool.id).then(function (response) {
      if (response.success) {
        $("#schoolModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateSchoolsData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getSchools(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  private getFilteredSchools(request) {
    let ref = this;
    this.schoolService.findSchools(request).then(
      function (response) {
        ref.schools = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  private initUniversities() {
    let ref = this;
    this.schoolService.getUniversities().then(function (response) {
      ref.universities = response.data;
    });
  }
}
