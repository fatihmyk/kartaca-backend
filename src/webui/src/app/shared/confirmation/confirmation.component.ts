import { Component, OnInit } from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Subject} from "rxjs";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  private body : string;
  private header : string;
  public onClose : Subject<boolean>;  // Evete mi tikladi hayira mi tikladi bi state'de tutuyoruz.
  private active : boolean;    //Ekranda mi pasif mi degil mi tutmak icin.

  constructor(private bsModalRef : BsModalRef) { }

  ngOnInit() {
    this.onClose = new Subject();
  }

  public showConfirmation(header:string, body:string) : void{
    this.body = body;
    this.header = header;
    this.active = true;
  }

  onConfirm(){
    this.active=false;
    this.onClose.next(true);
    this.bsModalRef.hide();
  }

  onCancel() {
    this.active=false;
    this.onClose.next(false);
    this.bsModalRef.hide();
  }
}
