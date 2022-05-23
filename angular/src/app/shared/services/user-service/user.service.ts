import { NgIf } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { Cart } from '../../interfaces/Cart-Interface/cart.interface';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';


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

  getUserActiveCart(id:number): Observable<any> {
    console.log(this.userURL + "/" + id + "/cart");
    return this.http.get<Cart>(this.userURL + "/" + id + "/cart", {withCredentials:true});
  }

}
