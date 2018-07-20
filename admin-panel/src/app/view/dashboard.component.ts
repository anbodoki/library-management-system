import {Component, OnInit} from "@angular/core";
import {DashboardService} from "../services/dashboard.service";
import {UserService} from "../services/user.service";
import {AccessService} from "../services/access.service";
import {School} from "../model/client/school";
import {Client} from "../model/client/client";
import {PagingLoader} from "../model/loader";
import {ResourceBorrow} from "../model/resources/resourceborrow";
import {ResourceBorrowService} from "../services/resourceborrow.service";
import {GeneralFilterRequest} from "../model/request/general-filter-request";

@Component({
  selector: "dashboard",
  templateUrl: "dashboard.component.html"
})

export class DashboardComponent implements OnInit {

  userCount: number;
  resourcesCount: number;
  loaded: boolean = false;

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
              private resourceBorrowService: ResourceBorrowService,
              private accessService: AccessService) {
    let ref =this;
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
}
