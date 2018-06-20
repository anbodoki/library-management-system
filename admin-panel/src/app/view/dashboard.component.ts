import {Component, OnInit} from "@angular/core";
import {DashboardService} from "../services/dashboard.service";
import {UserService} from "../services/user.service";
import {AccessService} from "../services/access.service";

@Component({
  selector: "dashboard",
  templateUrl: "dashboard.component.html"
})

export class DashboardComponent implements OnInit {

  userCount: number;
  resourcesCount: number;
  loaded: boolean = false;

  ngOnInit(): void {
    this.fetchData()
  }

  constructor(private dashboardService: DashboardService, private userService: UserService, private accessService: AccessService) {
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
}
