import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthenticationService } from '../../../../services/authentication.service';

import { blankValidator, customEmailValidator, passwordStrengthValidator } from '../../../../utils/validators.util';

import { IUserCreation } from '../../../../models/user-creation.model';
import { IErrorResponse } from '../../../../models/error-response.model';

import { UserTypeEnum } from '../../../../enumerations/user-type.enum';


@Component({
  templateUrl: './sign-up.component.html',
  styleUrls: [ './sign-up.component.scss' ]
})
export class SignUpComponent {

  userTypes: Array<UserTypeEnum> = Object.values(UserTypeEnum);

  signUpFormGroup: FormGroup = this.formBuilder.group({
    userType:                   [ '', [ Validators.required ] ],
    firstName:                  [ '', [ Validators.required ], [ blankValidator() ] ],
    lastName:                   [ '', [ Validators.required ], [ blankValidator() ] ],
    email:                      [ '', [ Validators.required ], [ customEmailValidator() ] ],
    password:                   [ '', [ Validators.required ], [ passwordStrengthValidator() ] ],
    passwordConfirmation:       [ '', [ Validators.required ] ]
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  onUserTypeSelection(userType: UserTypeEnum) {
    this.signUpFormGroup.get('userType').setValue(userType);
  }

  onSignUp() {
    if (this.signUpFormGroup.get('password').value !== this.signUpFormGroup.get('passwordConfirmation').value) {
      this.signUpFormGroup.get('passwordConfirmation').setErrors({ notConfirmed: true });
      return;
    }
    const userCreation: IUserCreation = this.signUpFormGroup.value;
    this.authenticationService.signUp(userCreation).subscribe(
      () => {
        this.router.navigateByUrl('/authentication/sign-in');
      },
      (error: IErrorResponse) => {
        console.error(error);
      }
    );
  }

}
