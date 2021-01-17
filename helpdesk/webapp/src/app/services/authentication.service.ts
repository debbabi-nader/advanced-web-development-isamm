import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { BehaviorSubject, Observable } from 'rxjs';

import { StorageService } from './storage.service';

import { ISignInCredentials } from '../models/sign-in-credentials.model';
import { IAuthenticationToken } from '../models/authentication-token.model';
import { IUserCreation } from '../models/user-creation.model';
import { IUser } from '../models/user.model';

import { StorageKeyEnum } from '../enumerations/storage-key.enum';


@Injectable()
export class AuthenticationService {

  private authenticationStatus: BehaviorSubject<boolean>;

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {

    if (this.storageService.getItem(StorageKeyEnum.AUTHENTICATION_TOKEN_KEY) &&
        this.storageService.getItem(StorageKeyEnum.CURRENT_USER_KEY)) {

      this.authenticationStatus = new BehaviorSubject<boolean>(true);

    } else {

      this.authenticationStatus = new BehaviorSubject<boolean>(false);

    }

  }

  signIn(signInCredentials: ISignInCredentials): Observable<IAuthenticationToken> {

    const url = 'http://localhost:8080/authentication/sign-in';

    return this.http.post<IAuthenticationToken>(url, signInCredentials);

  }

  signUp(userCreation: IUserCreation): Observable<IUser> {

    const url = 'http://localhost:8080/authentication/sign-up';

    return this.http.post<IUser>(url, userCreation);

  }

  setAuthenticationStatus(authenticationStatus: boolean): void {
    this.authenticationStatus.next(authenticationStatus);
  }

  getAuthenticationStatus(): Observable<boolean> {
    return this.authenticationStatus.asObservable();
  }

}
