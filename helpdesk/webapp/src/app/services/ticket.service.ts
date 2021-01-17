import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';

import { IPage } from '../models/page.model';
import { ITicket } from '../models/ticket.model';
import { ITicketCreation } from '../models/ticket-creation.model';
import { ITicketStatusCreation } from '../models/ticket-status-creation.model';
import { ITicketUpdate } from '../models/ticket-update.model';


@Injectable()
export class TicketService {

  constructor(
    private http: HttpClient
  ) { }

  getTicketById(ticketId: string): Observable<ITicket> {

    const url = 'http://localhost:8080/api/tickets/' + ticketId;

    return this.http.get<ITicket>(url);

  }

  getTickets(page: number, size: number, creatorId?: string): Observable<IPage<ITicket>> {

    const url = 'http://localhost:8080/api/tickets';
    let params: HttpParams = new HttpParams().set('page', String(page))
        .set('size', String(size));
    if (creatorId) {
      params = params.set('creatorId', creatorId);
    }
    const options = { params };

    return this.http.get<IPage<ITicket>>(url, options);

  }

  createTicket(ticketCreation: ITicketCreation): Observable<ITicket> {

    const url = 'http://localhost:8080/api/tickets';

    return this.http.post<ITicket>(url, ticketCreation);

  }

  updateTicket(ticketId: string, ticketUpdate: ITicketUpdate): Observable<ITicket> {

    const url = 'http://localhost:8080/api/tickets/' + ticketId;

    return this.http.put<ITicket>(url, ticketUpdate);

  }

  updateTicketStatus(ticketId: string, ticketStatusCreation: ITicketStatusCreation): Observable<ITicket> {

    const url = 'http://localhost:8080/api/tickets/' + ticketId;

    return this.http.post<ITicket>(url, ticketStatusCreation);

  }

  deleteTicket(ticketId: string): Observable<void> {

    const url = 'http://localhost:8080/api/tickets/' + ticketId;

    return this.http.delete<void>(url);

  }

}
