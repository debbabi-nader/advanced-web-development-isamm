import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { StorageService } from '../services/storage.service';

import { IErrorResponse } from '../models/error-response.model';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(
      catchError(
        (error: HttpErrorResponse) => {

          const errorResponse: IErrorResponse = error.error;
          const url = this.router.routerState.snapshot.url;

          if (error.status === 401 && !url.startsWith('/authentication/sign-in')) {
            this.storageService.clearStorage();
            this.router.navigateByUrl('/authentication/sign-in');
          }

          return throwError(errorResponse);

        }
      )
    );

  }

}
