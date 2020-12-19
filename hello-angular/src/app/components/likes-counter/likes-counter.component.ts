import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-likes-counter',
  templateUrl: './likes-counter.component.html',
  styleUrls: ['./likes-counter.component.scss']
})
export class LikesCounterComponent {

  @Input()
  likes: number;

}
