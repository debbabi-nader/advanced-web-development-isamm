import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthenticationService } from '../../../../services/authentication.service';
import { StorageService } from '../../../../services/storage.service';
import { UserService } from '../../../../services/user.service';

import { customEmailValidator } from '../../../../utils/validators.util';

import { ISignInCredentials } from '../../../../models/sign-in-credentials.model';
import { IAuthenticationToken } from '../../../../models/authentication-token.model';
import { IUser } from '../../../../models/user.model';
import { IErrorResponse } from '../../../../models/error-response.model';

import { StorageKeyEnum } from '../../../../enumerations/storage-key.enum';


@Component({
  templateUrl: './sign-in.component.html',
  styleUrls: [ './sign-in.component.scss' ]
})
export class SignInComponent implements OnInit {

  signInFormGroup: FormGroup = this.formBuilder.group({
    email:         [ '', [ Validators.required ], [ customEmailValidator() ] ],
    password:      [ '', [ Validators.required ] ]
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private storageService: StorageService,
    private userService: UserService,
  ) { }

  ngOnInit() {

    if (this.storageService.getItem(StorageKeyEnum.AUTHENTICATION_TOKEN_KEY) &&
        this.storageService.getItem(StorageKeyEnum.CURRENT_USER_KEY)) {

      this.router.navigateByUrl('/');

    } else {

      this.storageService.clearStorage();
      this.authenticationService.setAuthenticationStatus(false);

    }

  }

  onSignIn() {
    const signInCredentials: ISignInCredentials = this.signInFormGroup.value;
    this.authenticationService.signIn(signInCredentials).subscribe(
      (authenticationToken: IAuthenticationToken) => {
        this.storageService.setItem(StorageKeyEnum.AUTHENTICATION_TOKEN_KEY, authenticationToken);
        this.userService.getUserByEmail(signInCredentials.email).subscribe(
          (user: IUser) => {
            this.storageService.setItem(StorageKeyEnum.CURRENT_USER_KEY, user);
            this.authenticationService.setAuthenticationStatus(true);
            this.router.navigateByUrl('/');
          }
        );
      },
      (error: IErrorResponse) => {
        switch (error.status) {
          case 401:
            this.signInFormGroup.get('password').setErrors({ wrongPassword: true });
            break;
          case 404:
            this.signInFormGroup.get('email').setErrors({ accountNotFound: true });
            this.signInFormGroup.get('password').reset();
            break;
        }
      }
    );
  }

}
