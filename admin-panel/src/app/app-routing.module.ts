import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./view/dashboard.component";
import {UsersComponent} from "./view/security/users.component";
import {RolesComponent} from "./view/security/roles.component";
import {LanguagesComponent} from "./view/resources/languages.component";
import {ConfigurationPropertyComponent} from "./view/configuration/configuration-property.component";

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'users', component: UsersComponent},
  {path: 'roles', component: RolesComponent},
  {path: 'languages', component: LanguagesComponent},
  {path: 'configurationProperties', component: ConfigurationPropertyComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
