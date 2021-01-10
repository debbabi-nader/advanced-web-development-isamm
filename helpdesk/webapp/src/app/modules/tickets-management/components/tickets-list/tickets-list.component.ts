import { Component, OnInit } from '@angular/core';

import { IPage } from '../../../../models/page.model';
import { ITicket } from '../../../../models/ticket.model';

import { TicketService } from '../../services/ticket.service';


const PAGE_SIZE: number = 5;


@Component({
  templateUrl: './tickets-list.component.html',
  styleUrls: ['./tickets-list.component.scss']
})
export class TicketsListComponent implements OnInit {

  ticketsPage: IPage<ITicket>;

  get from(): number {
    return Math.min(this.ticketsPage?.totalElements, (this.ticketsPage?.index * this.ticketsPage?.size) + 1);
  }

  get to(): number {
    return Math.min(this.ticketsPage?.totalElements, (this.ticketsPage?.index + 1) * this.ticketsPage?.size);
  }

  constructor(
    private ticketService: TicketService
  ) { }

  ngOnInit() {
    this.loadTickets(0);
  }

  private loadTickets(page: number) {
    this.ticketService.getTickets('1bdc8a4d-0d9c-4e70-8444-5236e4136de9', page, PAGE_SIZE).subscribe(
      (ticketsPage: IPage<ITicket>) => {
        this.ticketsPage = ticketsPage;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  onPreviousPage(): void {
    if (!this.ticketsPage.first) {
      this.loadTickets(this.ticketsPage.index - 1);
    }
  }

  onNextPage(): void {
    if (!this.ticketsPage.last) {
      this.loadTickets(this.ticketsPage.index + 1);
    }
  }

}
