import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TravelService} from "../../../services/shared/travel.service";
import {UserService} from "../../../services/shared/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-travel-detail',
  templateUrl: './travel-detail.component.html',
  styleUrls: ['./travel-detail.component.scss']
})
export class TravelDetailComponent implements OnInit {

  @ViewChild('tplDateCell') tplDateCell: TemplateRef<any>;

  travelDetailForm: FormGroup;
  // History Table Options
  columns = [];

  // Route Parameter Options
  id: number;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private travelService: TravelService,
              private userService: UserService,
              private formBuilder: FormBuilder,) {
  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.loadTravelDetails();
    });

    this.columns = [{prop: 'id', name: 'No', maxWidth: 30},
      {prop: 'name', name: 'Name', sortable: false},
      {prop: 'date', name: 'Travel Date'},
      {prop: 'location', name: 'Location', sortable: false},
      {prop: 'notes', name: 'Notes', sortable: false},
      {prop: 'travelUser.nameSurname', name: 'User', sortable: false},
    ];

  }

  private loadTravelDetails() {
    this.travelService.getById(this.id).subscribe(response => {
      this.travelDetailForm = this.createTravelDetailFormGroup(response);
    });
  }

  createTravelDetailFormGroup(response) {
    return this.formBuilder.group({
      id: response['id'],
      name: response['name'],
      location: response['location'],
      date: this.fromJsonDate(response['date']),
      notes: response['notes'],
      user: response['user'] ? response ['user']['nameSurname'] : ''
    });
  }

  fromJsonDate(jDate): string {
    const bDate: Date = new Date(jDate);
    return bDate.toISOString().substring(0, 10);
  }

}
