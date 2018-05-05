import {Component, OnInit} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";
import {Language} from "../../model/resources/language";
import {LanguageService} from "../../services/language.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'languages-list',
  templateUrl: './languages.component.html',
})

export class LanguagesComponent implements OnInit {
  languages: Language[];
  selectedLanguage: Language;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;

  constructor(private accessService: AccessService,
              private languageService: LanguageService,
              private utils: Utils) {
    let ref =this;
    this.accessService.containsCode('language_manage').then(function (response) {
      console.log(response);
      ref.isManage = response;
    });
  }

  public getLanguages(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.languageService.findLanguagesQuick(request).then(
      function (response) {
        ref.languages = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
  }

  onSelect(language: Language): void {
    this.selectedLanguage = language;
  }

  showAddEditModal(language): void {
    this.selectedLanguage = <Language> JSON.parse(JSON.stringify(language.row));
    this.utils.setPrevObj(JSON.stringify(language.row));
    $("#languageModal").modal("show");
  }

  updateLanguage() {
    if (!this.utils.formIsValid('#languageModal')) {
      return;
    }
    let ref = this;
    this.languageService.update(this.selectedLanguage).then(function () {
      $("#languageModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteLanguage() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedLanguage = new Language();
    $("#languageModal").modal("show");
  }

  saveLanguage() {
    if (!this.utils.formIsValid('#languageModal')) {
      return;
    }
    let ref = this;
    this.languageService.create(this.selectedLanguage).then(function () {
      $("#languageModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.languageService.delete(this.selectedLanguage.id).then(function (response) {
      if (response.success) {
        $("#languageModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateLanguagesData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getLanguages(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  private getFilteredLanguages(request) {
    let ref = this;
    this.languageService.findLanguages(request).then(
      function (response) {
        ref.languages = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }
}
