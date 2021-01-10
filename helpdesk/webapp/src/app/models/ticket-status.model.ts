import { TicketStatusEnum } from '../enumerations/ticket-status.enum';


export interface ITicketStatus {

  id: string;
  status: TicketStatusEnum;
  timestamp: string;

}
