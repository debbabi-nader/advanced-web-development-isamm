import { ValidatorFn, AbstractControl } from '@angular/forms';

import { of } from 'rxjs';


export function customEmailValidator(): ValidatorFn {

  return (control: AbstractControl): { [ key: string ]: any } | null => {
    const customEmailRegex = /[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/g;
    return customEmailRegex.test(control.value) ? of(null) : of({ 'customEmail': { value: control.value } });
  };

}

export function passwordStrengthValidator(): ValidatorFn {

  return (control: AbstractControl): { [ key: string ]: any } | null => {
    const strongPasswordRegex = /^.*(?=.{8,})((?=.*[!@#$%^&*()\-_=+{};:,<.>]){1})(?=.*\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$/g;
    return strongPasswordRegex.test(control.value) ? of(null) : of({ 'weakPassword': { value: control.value } });
  };

}

export function blankValidator(): ValidatorFn {

  return (control: AbstractControl): { [ key: string ]: any } | null => {
    return (control.value as string).trim() !== '' ? of(null) : of({ 'blank': { value: control.value } });
  };

}
