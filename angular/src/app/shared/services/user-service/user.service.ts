import { NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';

import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';
import { UserAddress } from '../../interfaces/user-address.interface';
import { UserPayment } from '../../interfaces/user-payment.interface';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  userURL: string = "http://localhost:8080/user";

  private user: UserInfo = JSON.parse(localStorage.getItem('userInfo') || '{}');

  private currentUserSubject: BehaviorSubject<UserInfo> = new BehaviorSubject(
    this.user
  );

  getCurrentUser(): BehaviorSubject<UserInfo> {

    return this.currentUserSubject;
  }

  setUserId(userId:number) {
    this.user.userId = userId;
  }

  setActiveCartId(cartId:number) {
    this.user.activeCartId = cartId;
  }


  constructor(private http: HttpClient) {
  }

  getUser(id: number): Observable<any> {

    return this.http.get<UserProfile>(this.userURL + "/" + id + "/profile", {withCredentials:true});
  }

  getUserCart(cartId: number): Observable<any> {
    return this.http.get<UserProfile>(this.userURL + "/" + cartId + "/profile", {withCredentials:true});
  }

  updateUserAddress(address : UserAddress): Observable<any> {
    return this.http.put<UserAddress>(this.userURL + "/update/address", {withCredentials:true});
  }

  updateUserPayment(payment : UserPayment): Observable<any> {
    return this.http.put<UserPayment>(this.userURL + "/update/payment", {withCredentials:true});
  }


}
