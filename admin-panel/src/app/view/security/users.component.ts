import {Component, OnInit} from '@angular/core';
import {User} from '../../model/security/user';
import {UserService} from '../../services/user.service'
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {RoleService} from "../../services/role.service";
import {Role} from "../../model/security/role";
import {AccessService} from "../../services/access.service";
import {Utils} from "../../services/utils";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'users-list',
  templateUrl: './users.component.html',
})

export class UsersComponent implements OnInit {
  users: User[];
  selectedUser: User;
  passwordRepeated: string;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);
  roles: Role[];
  isManage: boolean;

  constructor(private userService: UserService,
              private roleService: RoleService,
              private accessService: AccessService,
              private utils: Utils) {
    let ref =this;
    this.accessService.containsCode('user_manage').then(function (response) {
      ref.isManage = response;
    });
  }

  public getUsers(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.userService.findUsersQuick(request).then(
      function (response) {
        ref.users = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
    let ref =this;
    this.roleService.findRolesQuick({limit: -1, offset: -1}).then(
      function (response) {
        ref.roles = response.data.resultList;
      }
    );
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }

  showAddEditModal(user): void {
    this.selectedUser = <User> JSON.parse(JSON.stringify(user.row));
    this.utils.showContent("userDetails");
    this.utils.setPrevObj(JSON.stringify(user.row));
    $("#userModal").modal("show");
  }

  updateUser() {
    if (!this.utils.formIsValid('#userModal')) {
      return;
    }
    let ref = this;
    this.userService.update(this.selectedUser).then(function () {
      $("#userModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  deleteUser() {
    $("#confirmModal .modal").modal("show");
  }

  showAddModal($event: Event) {
    this.selectedUser = new User;
    this.selectedUser.roles = [];
    this.passwordRepeated = '';
    this.utils.showContent("userDetails");
    $("#userModal").modal("show");
  }

  saveUser() {
    if (!this.utils.formIsValid('#userModal')) {
      return;
    }
    let ref = this;
    this.userService.create(this.selectedUser).then(function () {
      $("#userModal").modal("hide");
      ref.loader = ref.loader.load(true);
    });
  }

  onDeleteConfirmed() {
    let ref = this;
    this.userService.delete(this.selectedUser.id).then(function (response) {
      if (response.success) {
        $("#userModal").modal("hide");
        ref.loader = ref.loader.load(true);
      }
    });
  }

  updateUsersData(searchedTerm) {
    this.query = searchedTerm;
  }

  changePage(pageInfo) {
    this.getUsers(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  filterClicked(request) {
    this.getFilteredUsers(request);
  }

  private getFilteredUsers(request) {
    let ref = this;
    this.userService.findUsers(request).then(
      function (response) {
        ref.users = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  openImageModal(item) {
    this.selectedUser = <User> JSON.parse(JSON.stringify(item));
    this.utils.setPrevObj(JSON.stringify(item));
    $("users-list .uploadImageModal").modal("show");
  }
}
