import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthenticationGuard } from './guards/authentication.guard';


const APP_ROUTES: Routes = [
  {
    path: 'authentication',
    loadChildren: () => import('./modules/authentication/authentication.module').then(m => m.AuthenticationModule)
  },
  {
    path: 'tickets',
    loadChildren: () => import('./modules/tickets-management/tickets-management.module').then(m => m.TicketsManagementModule),
    canLoad: [ AuthenticationGuard ]
  },
  {
    path: '**',
    redirectTo: 'tickets',
    pathMatch: 'full'
  }
];


@NgModule({
  imports: [ RouterModule.forRoot(APP_ROUTES) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
