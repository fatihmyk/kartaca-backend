import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TravelComponent} from "./travel.component";
import {TravelDetailComponent} from "./travel-detail/travel-detail.component";



const routes: Routes = [

  {
    path: '',
    component: TravelComponent
  },{
    path: ':id', component: TravelDetailComponent
  }
];

@NgModule(
  {
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
export class TravelRoutingModule {}

