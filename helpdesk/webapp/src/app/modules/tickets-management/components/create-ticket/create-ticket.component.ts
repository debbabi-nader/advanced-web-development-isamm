import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { TicketService } from '../../../../services/ticket.service';
import { StorageService } from '../../../../services/storage.service';

import { blankValidator } from '../../../../utils/validators.util';

import { ITicketCreation } from '../../../../models/ticket-creation.model';
import { IUser } from '../../../../models/user.model';

import { StorageKeyEnum } from '../../../../enumerations/storage-key.enum';


@Component({
  templateUrl: './create-ticket.component.html',
  styleUrls: [ './create-ticket.component.scss' ]
})
export class CreateTicketComponent {

  currentUser: IUser;

  createTicketFormGroup: FormGroup = this.formBuilder.group({
    label:               [ '', [ Validators.required ], [ blankValidator() ] ],
    description:         [ '', [ Validators.maxLength(200) ] ]
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private ticketService: TicketService,
    private storageService: StorageService
  ) { }

  onSubmit() {

    const ticketCreation: ITicketCreation = this.createTicketFormGroup.value;
    ticketCreation.creatorId = this.storageService.getItem(StorageKeyEnum.CURRENT_USER_KEY)?.id;

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
