import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./view/dashboard.component";
import {UsersComponent} from "./view/security/users.component";
import {RolesComponent} from "./view/security/roles.component";
import {LanguagesComponent} from "./view/resources/languages.component";
import {ConfigurationPropertyComponent} from "./view/configuration/configuration-property.component";
import {MaterialTypeComponent} from "./view/resources/materialtype.component";
import {CategoryComponent} from "./view/resources/category.component";
import {ResourceComponent} from "./view/resources/resource.component";
import {SchoolComponent} from "./view/client/school.component";
import {ClientComponent} from "./view/client/client.component";

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'users', component: UsersComponent},
  {path: 'roles', component: RolesComponent},
  {path: 'languages', component: LanguagesComponent},
  {path: 'configurationProperties', component: ConfigurationPropertyComponent},
  {path: 'materialTypes', component: MaterialTypeComponent},
  {path: 'categories', component: CategoryComponent},
  {path: 'resources', component: ResourceComponent},
  {path: 'schools', component: SchoolComponent},
  {path: 'clients', component: ClientComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
