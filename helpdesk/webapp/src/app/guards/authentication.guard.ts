import { Injectable } from '@angular/core';
import { CanLoad, Route, Router } from '@angular/router';

import { StorageService } from '../services/storage.service';

import { StorageKeyEnum } from '../enumerations/storage-key.enum';


@Injectable()
export class AuthenticationGuard implements CanLoad {

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  canLoad(route: Route): boolean {

    if (!this.storageService.getItem(StorageKeyEnum.AUTHENTICATION_TOKEN_KEY) ||
        !this.storageService.getItem(StorageKeyEnum.CURRENT_USER_KEY)) {

      this.router.navigateByUrl('/authentication/sign-in');
      return false;

    } else {

      return true;

    }

  }

}
