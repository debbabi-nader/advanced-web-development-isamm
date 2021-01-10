import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { TicketsManagementRoutingModule } from './tickets-management-routing.module';

import { TicketService } from './services/ticket.service';

import { TicketsManagementComponent } from './tickets-management.component';
import { TicketsListComponent } from './components/tickets-list/tickets-list.component';
import { CreateTicketComponent } from './components/create-ticket/create-ticket.component';
import { TicketDetailsComponent } from './components/ticket-details/ticket-details.component';
import { EditTicketComponent } from './components/edit-ticket/edit-ticket.component';

import { TicketStatusPipe } from './pipes/ticket-status.pipe';


@NgModule({
  declarations: [
    TicketsManagementComponent,
    TicketsListComponent,
    CreateTicketComponent,
    TicketDetailsComponent,
    TicketStatusPipe,
    EditTicketComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    TicketsManagementRoutingModule
  ],
  providers: [
    TicketService
  ]
})
export class TicketsManagementModule { }
