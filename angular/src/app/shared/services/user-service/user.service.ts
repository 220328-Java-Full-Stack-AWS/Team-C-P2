import { NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, Subject } from 'rxjs';
import { UserInfo } from '../../interfaces/User-Interface/User-info.interface';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private instance?: Subject<UserInfo>;

  createInstance(): Subject<UserInfo> {
    const instance = new Subject<UserInfo>()
    return instance;
  }

  getInstance(): Subject<UserInfo> {
    if(!this.instance){
      this.instance = this.createInstance();
    }
    return this.instance;
  }

  userId: number = 0;
  activeCartId: number = 0;

  setUserId(id:number) {
    this.userId = id;
  }

  setActiveCartId(id:number) {
    this.activeCartId = id;
  }

  getUserId() {
    return this.userId;
  }

  getActiveCartId(){
    return this.activeCartId;
  }

  userURL: string = "http://localhost:8080/user";

  constructor(private http: HttpClient) {
  }

  getUser(id: number): Observable<any> {

    return this.http.get<UserProfile>(this.userURL + "/" + id + "/profile", {withCredentials:true});
  }
}