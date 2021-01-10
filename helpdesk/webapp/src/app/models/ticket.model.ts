import { IUser } from './user.model';
import { ITicketStatus } from './ticket-status.model';


export interface ITicket {

  id: string;
  label: string;
  description: string;
  createdBy: IUser;
  statuses: Array<ITicketStatus>;

}
