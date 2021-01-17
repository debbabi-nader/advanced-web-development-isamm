import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { TicketService } from '../../../../services/ticket.service';

import { blankValidator } from '../../../../utils/validators.util';

import { ITicket } from '../../../../models/ticket.model';
import { ITicketUpdate } from '../../../../models/ticket-update.model';


@Component({
  templateUrl: './edit-ticket.component.html',
  styleUrls: [ './edit-ticket.component.scss' ]
})
export class EditTicketComponent implements OnInit {

  ticket: ITicket;

  editTicketFormGroup: FormGroup = this.formBuilder.group({
    label:             [ '', [ Validators.required ], [ blankValidator() ] ],
    description:       [ '', [ Validators.maxLength(200) ] ]
  });

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private ticketService: TicketService
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      (paramMap: ParamMap) => {
        this.loadTicketById(paramMap.get('ticketId'));
      }
    );
  }

  private loadTicketById(ticketId: string) {
    this.ticketService.getTicketById(ticketId).subscribe(
      (ticket: ITicket) => {
        this.ticket = ticket;
        this.initEditTicketFormGroup();
      },
      (error: any) => {
        console.error(error);
        this.navigateBackToTicketsList();
      }
    );
  }

  private initEditTicketFormGroup() {
    this.editTicketFormGroup.get('label').setValue(this.ticket.label);
    this.editTicketFormGroup.get('description').setValue(this.ticket.description);
  }

  private navigateBackToTicketsList() {
    this.router.navigateByUrl('/tickets');
  }

  onSubmit() {

    const ticketUpdate: ITicketUpdate = this.editTicketFormGroup.value;

    this.ticketService.updateTicket(this.ticket.id, ticketUpdate).subscribe(
      () => {
        this.navigateBackToTicketDetails();
      },
      (error: any) => {
        console.error(error);
      }
    );

  }

  onCancel() {
    this.navigateBackToTicketDetails();
  }

  private navigateBackToTicketDetails() {
    this.router.navigateByUrl('/tickets/ticket-details/' + this.ticket.id);
  }

}
