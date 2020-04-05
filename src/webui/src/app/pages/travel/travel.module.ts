import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TravelComponent } from './travel.component';
import {TravelRoutingModule} from "./travel.routing.module";
import {TravelService} from "../../services/shared/travel.service";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {SharedModule} from "../../shared/shared.module";
import {TravelDetailComponent} from "./travel-detail/travel-detail.component";

@NgModule({
  declarations: [TravelComponent, TravelDetailComponent],
    imports: [
        CommonModule,
        TravelRoutingModule,
        NgxDatatableModule,
        SharedModule,
    ],
  providers : [TravelService]
})
export class TravelModule { }
