import { Injectable } from '@angular/core';

import { StorageKeyEnum } from '../enumerations/storage-key.enum';


@Injectable()
export class StorageService {

  setItem(key: StorageKeyEnum, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  getItem(key: StorageKeyEnum): any {
    const value: string = localStorage.getItem(key);
    if (!value || value === '{}' || value.trim() === '') {
      return null;
    } else {
      return JSON.parse(value);
    }
  }

  removeItem(key: StorageKeyEnum): void {
    localStorage.removeItem(key);
  }

  clearStorage(): void {
    Object.values(StorageKeyEnum).forEach(key => this.removeItem(key));
  }

}
