import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const APP_ROUTES: Routes = [
  {
    path: 'tickets',
    loadChildren: () => import('./modules/tickets-management/tickets-management.module').then(m => m.TicketsManagementModule)
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
