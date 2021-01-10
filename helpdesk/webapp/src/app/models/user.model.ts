import { UserTypeEnum } from '../enumerations/user-type.enum';


export interface IUser {

  id: string;
  firstName: string;
  lastName: string;
  email: string;
  userType: UserTypeEnum;

}
