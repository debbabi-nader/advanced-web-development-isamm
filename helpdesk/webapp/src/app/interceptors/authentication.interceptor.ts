import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs';

import { StorageService } from '../services/storage.service';

import { IAuthenticationToken } from '../models/authentication-token.model';

import { StorageKeyEnum } from '../enumerations/storage-key.enum';


@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(
    private storageService: StorageService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const authenticationToken: IAuthenticationToken = this.storageService.getItem(StorageKeyEnum.AUTHENTICATION_TOKEN_KEY);

    if (authenticationToken) {
      request = request.clone({
        setHeaders: {
          'Authorization': 'Bearer ' + authenticationToken.token
        }
      });
    }

    return next.handle(request);

  }

}
