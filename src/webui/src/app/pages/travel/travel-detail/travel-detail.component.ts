import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/shared/issue.service";
import {TravelService} from "../../../services/shared/travel.service";
import {UserService} from "../../../services/shared/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-travel-detail',
  templateUrl: './travel-detail.component.html',
  styleUrls: ['./travel-detail.component.scss']
})
export class TravelDetailComponent implements OnInit {

  @ViewChild('tplDateCell') tplDateCell : TemplateRef<any>;

  //issueDetail={};   //ARRAY DEGIL OBJECT. JSONDA OBJECT OLARAK DURUYOR.
  travelDetailForm: FormGroup;
  // History Table Options
  datatable_rows=[];
  columns=[];

  // Route Parameter Options
  id: number;
  private sub: any;

  //DropDown Values
  issueStatusOptions=[];
  travelOptions=[];
  assigneeOptions=[];

  constructor(private route:ActivatedRoute,
              private issueService:IssueService,
              private travelService:TravelService,
              private userService:UserService,
              private formBuilder: FormBuilder,) { }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id= +params['id'];
      this.loadIssueDetails();
    });

    this.columns = [{prop:'id', name:'No', maxWidth:30 },
      { prop:'description', name: 'Description', sortable:false },
      { prop:'date', name: 'Issue Date' , cellTemplate : this.tplDateCell  },
      { prop:'issueStatus', name: 'Issue Status' , sortable:false },
      { prop:'assignee.nameSurname', name: 'Assignee' , sortable:false },
      { prop:'issue.travel.travelName', name: 'Project Name' }
    ];


    //  1- Project DropDown dolacak
    this.loadProjects();
    //  2- Assignee DD dolacak
    this.loadAssignees();
    //  3- Issue Status DD dolacak.
    this.loadIssueStatuses();

  }

  private loadIssueStatuses() {
    this.issueService.getAllIssueStatuses().subscribe(response => {
      this.issueStatusOptions = response;
    });
  }

  private loadProjects() {

    this.travelService.getAllNP().subscribe(response => {
      this.travelOptions = response;
    });

  }

  private loadAssignees() {
    this.userService.getAll().subscribe(response => {
      this.assigneeOptions = response;
    });
  }

  private loadIssueDetails() {
    this.issueService.getByIdWithDetails(this.id).subscribe(response => {
      this.travelDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  createIssueDetailFormGroup(response) {
    return this.formBuilder.group({
      id: response['id'],
      description: response['description'],
      details: response['details'],
      date: this.fromJsonDate(response['date']),
      issueStatus: response['issueStatus'],
      assignee_id: response['assignee'] ? response ['assignee']['id']:'',
      travel_id: response['travel'] ? response['travel']['id']: '',
      travel_manager: response['travel'] && response['travel']['manager'] ? response['travel']['manager']['nameSurname']: ''
    });
  }



    fromJsonDate(jDate): string {
      const bDate: Date = new Date(jDate);
      return bDate.toISOString().substring(0,10);
    }


}
