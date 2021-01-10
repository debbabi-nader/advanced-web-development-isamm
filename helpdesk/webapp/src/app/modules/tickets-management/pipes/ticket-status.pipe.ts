import { Pipe, PipeTransform } from '@angular/core';

import { TicketStatusEnum } from '../../../enumerations/ticket-status.enum';


@Pipe({
  name: 'ticketStatus'
})
export class TicketStatusPipe implements PipeTransform {

  transform(ticketStatus: TicketStatusEnum): string {

    switch (ticketStatus) {
      case TicketStatusEnum.OPEN:
        return 'Open';
      case TicketStatusEnum.IN_PROGRESS:
        return 'In progress';
      case TicketStatusEnum.BLOCKED:
        return 'Blocked';
      case TicketStatusEnum.CLOSED:
        return 'Closed';
      default:
        return 'Unknown status';
    }

  }

}
