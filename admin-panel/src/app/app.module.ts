import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {BaseService} from "./services/base.service";
import {LMSAlertModal} from "./lms-components/modals/lms-alert.modal";
import {DpDatePickerModule} from 'ng2-date-picker';
import {AccessService} from "./services/access.service";
import {CustomFormsModule} from 'ng4-validators'
import {DashboardComponent} from "./view/dashboard.component";
import {Utils} from "./services/utils";
import {RoleService} from "./services/role.service";
import {PrivilegeService} from "./services/privilege.service";
import {UserService} from "./services/user.service";
import {UsersComponent} from "./view/security/users.component";
import {LMSColorPickerComponent} from "./lms-components/lms-fields/lms-color-picker.component";
import {LMSCheckboxComponent} from "./lms-components/lms-fields/lms-checkbox.component";
import {LMSTreeCheckboxComponent} from "./lms-components/lms-fields/lms-tree-checkbox.component";
import {LMSComboComponent} from "./lms-components/lms-fields/lms-combo.component";
import {LMSDynamicComboColumnComponent} from "./lms-components/lms-fields/lms-dynamic-combo-column.component";
import {LMSDynamicComboComponent} from "./lms-components/lms-fields/lms-dynamic-combo.component";
import {LMSInputComponent} from "./lms-components/lms-fields/lms-input.component";
import {LMSMultiCheckboxComponent} from "./lms-components/lms-fields/lms-multi-checkbox.component";
import {LMSTextareaComponent} from "./lms-components/lms-fields/lms-textarea.component";
import {RolesComponent} from "./view/security/roles.component";
import {LMSConfirmModalComponent} from "./lms-components/modals/lms-confirm.modal";
import {LMSTableComponent} from "./lms-components/lms-table/lms-table.component";
import {LMSColumnComponent} from "./lms-components/lms-table/lms-column.component";
import {LanguagesComponent} from "./view/resources/languages.component";
import {LanguageService} from "./services/language.service";
import {ConfigurationPropertyComponent} from "./view/configuration/configuration-property.component";
import {ConfigurationPropertyService} from "./services/configuration-property.service";
import {MaterialTypeComponent} from "./view/resources/materialtype.component";
import {MaterialTypeService} from "./services/materialtype.service";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LMSAlertModal,
    AppComponent,
    UsersComponent,
    RolesComponent,
    LanguagesComponent,
    ConfigurationPropertyComponent,
    MaterialTypeComponent,
    DashboardComponent,
    LMSColorPickerComponent,
    LMSCheckboxComponent,
    LMSTreeCheckboxComponent,
    LMSComboComponent,
    LMSDynamicComboColumnComponent,
    LMSDynamicComboComponent,
    LMSInputComponent,
    LMSMultiCheckboxComponent,
    LMSTextareaComponent,
    LMSConfirmModalComponent,
    LMSTableComponent,
    LMSColumnComponent
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
    Utils,
    RoleService,
    PrivilegeService,
    UserService,
    LanguageService,
    ConfigurationPropertyService,
    MaterialTypeService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

}
