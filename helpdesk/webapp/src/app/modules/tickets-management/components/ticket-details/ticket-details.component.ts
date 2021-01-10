import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

import { TicketService } from '../../services/ticket.service';

import { ITicket } from '../../../../models/ticket.model';
import { ITicketStatusCreation } from '../../../../models/ticket-status-creation.model';

import { TicketStatusEnum } from '../../../../enumerations/ticket-status.enum';


@Component({
  templateUrl: './ticket-details.component.html',
  styleUrls: [ './ticket-details.component.scss' ]
})
export class TicketDetailsComponent implements OnInit {

  ticket: ITicket;

  ticketStatusEnum = TicketStatusEnum;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
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
      },
      (error: any) => {
        console.error(error);
        this.navigateBackToTicketsList();
      }
    );
  }

  onEdit() {
    this.router.navigateByUrl('/tickets/edit-ticket/' + this.ticket?.id);
  }

  onDelete() {
    this.ticketService.deleteTicket(this.ticket?.id).subscribe(
      () => {
        this.navigateBackToTicketsList();
      },
      (error: any) => {
        console.log(error);
      }
    )
  }

  private navigateBackToTicketsList() {
    this.router.navigateByUrl('/tickets');
  }

  onEditTicketStatus(ticketStatus: TicketStatusEnum) {

    const ticketStatusCreation: ITicketStatusCreation = {
      status: ticketStatus
    };

    this.ticketService.updateTicketStatus(this.ticket.id, ticketStatusCreation).subscribe(
      (ticket: ITicket) => {
        this.ticket = ticket;
      },
      (error: any) => {
        console.log(error);
      }
    );

  }

}
