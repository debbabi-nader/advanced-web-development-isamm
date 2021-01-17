import { Pipe, PipeTransform } from '@angular/core';

import { UserTypeEnum } from '../../../enumerations/user-type.enum';


@Pipe({
  name: 'userType'
})
export class UserTypePipe implements PipeTransform {

  transform(userType: UserTypeEnum): string {

    switch (userType) {
      case UserTypeEnum.CLIENT:
        return 'Client';
      case UserTypeEnum.OPERATOR:
        return 'Operator';
      default:
        return 'Unknown user type';
    }

  }

}
