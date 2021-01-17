import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { Subscription } from 'rxjs';

import { AuthenticationService } from './services/authentication.service';
import { StorageService } from './services/storage.service';

import { IUser } from './models/user.model';

import { StorageKeyEnum } from './enumerations/storage-key.enum';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.scss' ]
})
export class AppComponent implements OnInit, OnDestroy {

  authenticationStatusSubscription: Subscription;
  authenticationStatus: boolean;

  currentUser: IUser;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.authenticationStatusSubscription = this.authenticationService.getAuthenticationStatus().subscribe(
      (authenticationStatus: boolean) => {
        this.authenticationStatus = authenticationStatus;
        this.currentUser = this.storageService.getItem(StorageKeyEnum.CURRENT_USER_KEY);
      }
    );
  }

  onSignOut() {
    this.storageService.clearStorage();
    this.authenticationService.setAuthenticationStatus(false);
    this.router.navigateByUrl('/authentication/sign-in');
  }

  ngOnDestroy() {
    this.authenticationStatusSubscription?.unsubscribe();
  }

}
