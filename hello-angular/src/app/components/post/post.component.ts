import { Component, EventEmitter, Input, Output } from '@angular/core';

import { IPost } from 'src/app/models/post.model';


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent {

  @Input()
  post: IPost;

  @Output()
  like: EventEmitter<string> = new EventEmitter<string>();

  onLikeButtonClick(likedPostTitle: string): any {
    this.like.emit(likedPostTitle);
  }

}
