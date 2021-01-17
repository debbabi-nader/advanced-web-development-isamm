import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TicketsManagementComponent } from './tickets-management.component';
import { TicketsListComponent } from './components/tickets-list/tickets-list.component';
import { CreateTicketComponent } from './components/create-ticket/create-ticket.component';
import { TicketDetailsComponent } from './components/ticket-details/ticket-details.component';
import { EditTicketComponent } from './components/edit-ticket/edit-ticket.component';


const TICKETS_MANAGEMENT_ROUTES: Routes = [
  {
    path: '',
    component: TicketsManagementComponent,
    children: [
      {
        path: '',
        component: TicketsListComponent
      },
      {
        path: 'create-ticket',
        component: CreateTicketComponent
      },
      {
        path: 'ticket-details/:ticketId',
        component: TicketDetailsComponent
      },
      {
        path: 'edit-ticket/:ticketId',
        component: EditTicketComponent
      }
    ]
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];


@NgModule({
  imports: [ RouterModule.forChild(TICKETS_MANAGEMENT_ROUTES) ],
  exports: [ RouterModule ]
})
export class TicketsManagementRoutingModule { }
