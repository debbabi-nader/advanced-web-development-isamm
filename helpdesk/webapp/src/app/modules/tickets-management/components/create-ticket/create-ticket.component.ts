import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { TicketService } from '../../services/ticket.service';

import { ITicketCreation } from '../../../../models/ticket-creation.model';


@Component({
  templateUrl: './create-ticket.component.html',
  styleUrls: [ './create-ticket.component.scss' ]
})
export class CreateTicketComponent {

  createTicketFormGroup: FormGroup = this.formBuilder.group({
    label:          [ '', [ Validators.required ] ],
    description:    [ '', [ Validators.maxLength(200) ] ]
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private ticketService: TicketService
  ) { }

  onSubmit() {

    const ticketCreation: ITicketCreation = this.createTicketFormGroup.value;
    ticketCreation.creatorId = '1bdc8a4d-0d9c-4e70-8444-5236e4136de9';

    this.ticketService.createTicket(ticketCreation).subscribe(
      () => {
        this.navigateBackToTicketsList();
      },
      (error: any) => {
        console.error(error);
      }
    );

  }

  onCancel() {
    this.navigateBackToTicketsList();
  }

  private navigateBackToTicketsList() {
    this.router.navigateByUrl('/tickets');
  }

}
