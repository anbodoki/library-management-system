import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {BaseService} from "./services/base.service";
import {CapAlertModal} from "./lms-components/modals/lms-alert.modal";
import {DpDatePickerModule} from 'ng2-date-picker';
import {AccessService} from "./services/access.service";
import {CustomFormsModule} from 'ng4-validators'
import {DashboardComponent} from "./view/dashboard.component";
import {Utils} from "./services/utils";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CapAlertModal
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    DpDatePickerModule,
    CustomFormsModule
  ],
  providers: [
    BaseService,
    AccessService,
    Utils
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

}
