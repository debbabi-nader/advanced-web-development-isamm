import { Component } from '@angular/core';

import { IPost } from './models/post.model';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  username = '3ING';

  posts: Array<IPost> = [
    {
      title: 'Post 1',
      abstract: 'Velit aute ex cillum mollit tempor aliqua.',
      liked: false
    },
    {
      title: 'Post 2',
      abstract: 'Cillum fugiat dolore amet nostrud elit velit irure deserunt ipsum cillum eiusmod ut non.',
      liked: false
    },
    {
      title: 'Post 3',
      abstract: 'Mollit officia voluptate labore pariatur ea in dolor sit nulla dolor.',
      liked: false
    },
    {
      title: 'Post 4',
      abstract: 'Cupidatat laborum amet qui do ex ipsum ipsum in ut.',
      liked: false
    },
    {
      title: 'Post 5',
      abstract: 'Eiusmod nulla ex dolor aliqua Lorem cillum laborum nulla.',
      liked: false
    }
  ];

  likedPosts: number;

  onLike(title: string): any {
    console.log(title);
    const post: IPost = this.posts.find(p => p.title === title);
    post.liked = !post.liked;
    this.likedPosts = this.countLikedPosts();
  }

  private countLikedPosts(): number {
    return this.posts.filter(p => p.liked).length;
  }

}
