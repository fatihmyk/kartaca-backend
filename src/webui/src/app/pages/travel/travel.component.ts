import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {TravelService} from "../../services/shared/travel.service";
import {Page} from "../../common/page";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConfirmationComponent} from "../../shared/confirmation/confirmation.component";
import {UserService} from "../../services/shared/user.service";

@Component({
  selector: 'app-travel',
  templateUrl: './travel.component.html',
  styleUrls: ['./travel.component.scss']
})
export class TravelComponent implements OnInit {

  modalRef: BsModalRef;
  travelForm : FormGroup;
  @ViewChild('tplProjectDeleteCell') tplProjectDeleteCell : TemplateRef<any>;

  page = new Page();
  cols =[];
  rows = [];
  managerOptions = [];
  activeUser = {};

  constructor(private travelService : TravelService,
              private modalService: BsModalService,
              private formBuilder: FormBuilder,
              private userService: UserService) { }

  ngOnInit() {

    this.activeUser = JSON.parse(localStorage.getItem('currentUser'));

    this.cols = [{prop:'id', name:'No'},
      { prop:'name', name: 'Travel Name', sortable:false },
      { prop:'date', name: 'Travel Date' , sortable:true },
      { prop:'user.nameSurname', name: 'User' , sortable:false },
      { prop:'id', name: 'Actions' , cellTemplate: this.tplProjectDeleteCell, flexGrow:1 ,sortable:false }
      ];

    this.setPage({ offset: 0 });

    //this.travelService.getAll().subscribe((resp) => {console.log(resp)}); deneme
       this.travelForm = this.formBuilder.group({
         'name': [null, [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
         'location': [null, [Validators.required, Validators.minLength(4)]],
         'notes': [null, [Validators.required]],
         'user' :[this.activeUser]
       });

    this.userService.getAll().subscribe(res => {
      this.managerOptions = res;
      console.log(res);
    });
  }
  get f() { return this.travelForm.controls }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  saveTravel() {
   /* debugger*/
    if(!this.travelForm.valid)
      return;

    this.travelService.createTravel(this.travelForm.value).subscribe(
      response => {
        this.setPage({ offset: 0 });
        this.closeAndResetModal();
      }
    )

  }

  closeAndResetModal(){
    this.travelForm.reset();
    this.modalRef.hide();
  }

  setPage(pageInfo) {
    this.page.page=pageInfo.offset;
    this.travelService.getAll(this.page).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.totalElements = pagedData.totalElements;
      this.page.page = pagedData.page;
      this.rows = pagedData.content;
    });
  }

  showDeleteConfirmation(){
    const modal = this.modalService.show(ConfirmationComponent);
    (<ConfirmationComponent>modal.content).showConfirmation(
      'Test Header Content',
      'Test Body Content'
    );

    (<ConfirmationComponent>modal.content).onClose.subscribe( result => {

      if (result===true){
        console.log("YES");
      }
      else if (result ===false){
        console.log("NO");
      } }
    );

  }

  showProjectDeleteConfirmation(value) {
    const modal = this.modalService.show(ConfirmationComponent);
    (<ConfirmationComponent>modal.content).showConfirmation(
      'Delete Confirmation',
      'Are you sure for delete travel?'
    );

    (<ConfirmationComponent>modal.content).onClose.subscribe( result => {

      if (result===true){
        this.travelService.delete(value).subscribe(response =>{
        if (response ===true){
          this.setPage({offset:0})
        }
        });
      }

      else if (result ===false){}
    }
    );

  }
}
