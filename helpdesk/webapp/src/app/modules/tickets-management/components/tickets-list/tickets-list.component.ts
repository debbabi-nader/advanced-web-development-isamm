import { Component, OnInit } from '@angular/core';

import { IPage } from '../../../../models/page.model';
import { ITicket } from '../../../../models/ticket.model';

import { TicketService } from '../../../../services/ticket.service';
import { StorageService } from '../../../../services/storage.service';

import { IUser } from '../../../../models/user.model';

import { StorageKeyEnum } from '../../../../enumerations/storage-key.enum';
import { UserTypeEnum } from '../../../../enumerations/user-type.enum';


const PAGE_SIZE: number = 5;


@Component({
  templateUrl: './tickets-list.component.html',
  styleUrls: ['./tickets-list.component.scss']
})
export class TicketsListComponent implements OnInit {

  currentUser: IUser;

  userTypeEnum = UserTypeEnum;

  ticketsPage: IPage<ITicket>;

  get from(): number {
    return Math.min(this.ticketsPage?.totalElements, (this.ticketsPage?.index * this.ticketsPage?.size) + 1);
  }

  get to(): number {
    return Math.min(this.ticketsPage?.totalElements, (this.ticketsPage?.index + 1) * this.ticketsPage?.size);
  }

  constructor(
    private ticketService: TicketService,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.currentUser = this.storageService.getItem(StorageKeyEnum.CURRENT_USER_KEY);
    this.loadTickets(0);
  }

  private loadTickets(page: number) {
    const creatorId = this.currentUser?.userType === UserTypeEnum.CLIENT ? this.currentUser?.id : undefined;
    this.ticketService.getTickets(page, PAGE_SIZE, creatorId).subscribe(
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
