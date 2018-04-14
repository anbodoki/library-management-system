import {Component, OnInit} from '@angular/core';
import {Role} from '../../model/security/role';
import {RoleService} from '../../services/role.service'
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {PrivilegeService} from "../../services/privilege.service";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'roles-list',
  templateUrl: './roles.component.html',
})

export class RolesComponent implements OnInit {

  roles: Role[];
  privileges: {};
  selectedRole: Role;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  isManage: boolean;

  constructor(private roleService: RoleService, private privilegeService: PrivilegeService, private accessService: AccessService, private utils: Utils) {
    let ref =this;
    this.accessService.containsCode('role_manage').then(function (response) {
      ref.isManage = response;
    })
  }

  ngOnInit(): void {
    let ref = this;
    this.privilegeService.findPrivileges().then(function (resposnse) {
      ref.privileges = resposnse.data;
    });
  }

  public getRoles(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.roleService.findRolesQuick(request).then(
      function (response) {
        ref.roles = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  onSelect(role: Role): void {
    this.selectedRole = role;
  }

  showAddEditModal(role): void {
    this.selectedRole = <Role> JSON.parse(JSON.stringify(role.row));
    this.utils.setPrevObj(JSON.stringify(role.row));
    $("#roleModal").modal("show");
  }

  updateRole() {
    if (!this.utils.formIsValid('#roleModal')) {
      return;
    }
    let ref = this;
    this.roleService.update(this.selectedRole).then(function () {
      $("#roleModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteRole() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedRole = new Role;
    $("#roleModal").modal("show");
  }

  saveRole() {
    if (!this.utils.formIsValid('#roleModal')) {
      return;
    }
    let ref = this;
    this.roleService.create(this.selectedRole).then(function () {
      $("#roleModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.roleService.delete(this.selectedRole.id).then(function (response) {
      if (response.success) {
        $("#roleModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateRolesData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getRoles(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }
}
