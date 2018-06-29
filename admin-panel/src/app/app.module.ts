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
import {CategoryComponent} from "./view/resources/category.component";
import {CategoryService} from "./services/category.service";
import {FormUploadComponent} from "./lms-components/lms-fields/lms-upload.component";
import {LmsUploadModal} from "./lms-components/lms-fields/lms-upload-modal.component";
import {UploadFileService} from "./services/upload-file.service";
import {ResourceComponent} from "./view/resources/resource.component";
import {ResourceService} from "./services/resource.service";
import {DashboardService} from "./services/dashboard.service";
import {SchoolComponent} from "./view/client/school.component";
import {SchoolService} from "./services/school.service";
import {LmsDynamicComboComponent} from "./lms-components/lms-fields/lms-dynamic-combo.component";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LMSAlertModal,
    AppComponent,
    UsersComponent,
    RolesComponent,
    LanguagesComponent,
    SchoolComponent,
    ConfigurationPropertyComponent,
    MaterialTypeComponent,
    CategoryComponent,
    ResourceComponent,
    DashboardComponent,
    LMSColorPickerComponent,
    LMSCheckboxComponent,
    LMSTreeCheckboxComponent,
    LMSComboComponent,
    LMSDynamicComboColumnComponent,
    LmsDynamicComboComponent,
    LMSInputComponent,
    LMSMultiCheckboxComponent,
    LMSTextareaComponent,
    LMSConfirmModalComponent,
    LMSTableComponent,
    LMSColumnComponent,
    FormUploadComponent,
    LmsUploadModal
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
    MaterialTypeService,
    CategoryService,
    UploadFileService,
    ResourceService,
    DashboardService,
    SchoolService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

}
