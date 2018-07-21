import {Component, OnInit} from "@angular/core";
import {DashboardService} from "../services/dashboard.service";
import {UserService} from "../services/user.service";
import {AccessService} from "../services/access.service";
import {Client} from "../model/client/client";
import {PagingLoader} from "../model/loader";
import {ResourceBorrow} from "../model/resources/resourceborrow";
import {ResourceBorrowService} from "../services/resourceborrow.service";
import {GeneralFilterRequest} from "../model/request/general-filter-request";
import {Mail} from "../model/client/mail";
import {Utils} from "../services/utils";
import {MailService} from "../services/mail.service";

declare let jquery: any;
declare let $: any;

@Component({
  selector: "dashboard",
  templateUrl: "dashboard.component.html"
})

export class DashboardComponent implements OnInit {

  userCount: number;
  resourcesCount: number;
  loaded: boolean = false;
  mail: Mail;

  borrows: ResourceBorrow[];
  selectedResourceBorrow: Client;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);

  ngOnInit(): void {
    this.fetchData();
  }

  constructor(private dashboardService: DashboardService,
              private userService: UserService,
              private utils: Utils,
              private mailService: MailService,
              private resourceBorrowService: ResourceBorrowService,
              private accessService: AccessService) {
    let ref = this;
    if (!this.accessService.getPrivileges()) {
      this.userService.getUser().then(function (response) {
        if (response.success) {
          ref.accessService.setPrivileges(response.data.privileges);
        }
      });
    }
  }

  private fetchData() {
    let ref = this;
    this.dashboardService.initialData().then(function (response) {
      console.log(response);
      ref.userCount = response['data']['userCount'];
      ref.resourcesCount = response['data']['resourcesCount'];
      ref.loaded = true;
    });
  }

  public getResourceBorrows(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.resourceBorrowService.findResourceBorrowsQuick(request).then(
      function (response) {
        ref.borrows = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  changePage(pageInfo) {
    this.getResourceBorrows(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  private getFilteredResourceBorrows(request) {
    let ref = this;
    this.resourceBorrowService.findResourceBorrows(request).then(
      function (response) {
        ref.borrows = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  filterClicked(request) {
    this.getFilteredResourceBorrows(request);
  }

  sendMail(clientId) {
    console.log("");
    this.mail = new Mail();
    this.mail.clientId = clientId;
    $("#sendMailModal").modal("show");
  }

  sendMailToServer() {
    if (!this.utils.formIsValid('#sendMailModal')) {
      return;
    }
    let ref = this;
    this.mailService.sendMail(this.mail).then(function () {
      $("#sendMailModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }
}
