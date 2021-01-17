import { UserTypeEnum } from '../enumerations/user-type.enum';


export interface IUserCreation {

  firstName: string;
  lastName: string;
  email: string;
  userType: UserTypeEnum;
  password: string;
  passwordConfirmation: string;

}
