import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';

import { IUser } from '../models/user.model';


@Injectable()
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getUserByEmail(email: string): Observable<IUser> {

    const url = 'http://localhost:8080/api/users';

    const params: HttpParams = new HttpParams().set('email', email);
    const options = { params };

    return this.http.get<IUser>(url, options);

  }

}
