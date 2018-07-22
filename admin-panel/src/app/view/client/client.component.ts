import {Component, OnInit} from '@angular/core';
import {GeneralFilterRequest} from "../../model/request/general-filter-request";
import {PagingLoader} from "../../model/loader";
import {Client} from "../../model/client/client";
import {ClientService} from "../../services/client.service";
import {SchoolService} from "../../services/school.service";
import {School} from "../../model/client/school";
import {Utils} from "../../services/utils";

declare let jquery: any;
declare let $: any;

@Component({
  selector: 'client-list',
  templateUrl: './client.component.html',
})

export class ClientComponent implements OnInit {
  clients: Client[];
  schools: School[];
  selectedClient: Client;
  query: string;
  pageNum: number;
  loader: PagingLoader = new PagingLoader(true, true);

  constructor(private clientService: ClientService,
              private schoolService: SchoolService,
              private utils: Utils) {
    this.loadSchools("")
  }

  public getClients(limit, offset) {
    let ref = this;
    let request: GeneralFilterRequest = {query: this.query, limit: limit, offset: offset};
    this.clientService.findClientsQuick(request).then(
      function (response) {
        ref.clients = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  ngOnInit(): void {
  }

  showCardsModal(client) {
    this.selectedClient = <Client> JSON.parse(JSON.stringify(client.row));
    this.utils.setPrevObj(JSON.stringify(client.row));
    $("#cardsModal").modal("show");
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  updateCards() {
    if (!this.utils.formIsValid('#cardsModal')) {
      return;
    }
    let ref = this;
    this.clientService.update(this.selectedClient).then(function () {
      $("#cardsModal").modal("hide");
      ref.loader = ref.loader.load(false);
    });
  }

  changePage(pageInfo) {
    this.getClients(pageInfo.limit, (pageInfo.page) * pageInfo.limit);
  }

  updateClientsData(searchedTerm) {
    this.query = searchedTerm;
  }

  private getFilteredClients(request) {
    let ref = this;
    this.clientService.findClients(request).then(
      function (response) {
        ref.clients = response.data.resultList;
        ref.pageNum = response.data.pageNum;
      }
    );
  }

  filterClicked(request) {
    this.getFilteredClients(request);
  }

  private loadSchools(query) {
    let ref = this;
    this.schoolService.findSchools({query: query, limit: 10, offset: 0}).then(function (response) {
      if (response.success) {
        ref.schools = response.data.resultList;
      }
    });
  }

  activate(client: Client) {
    let ref = this;
    this.clientService.activate(client).then(function (response) {
      if (response.success) {
        ref.loader = ref.loader.load(true);
      }
    });
  }

  deactivate(client: Client) {
    let ref = this;
    this.clientService.deactivate(client).then(function (response) {
      if (response.success) {
        ref.loader = ref.loader.load(true);
      }
    });
  }
}
