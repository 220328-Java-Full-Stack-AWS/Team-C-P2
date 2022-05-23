import { NgIf } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { Cart } from '../../interfaces/Cart-Interface/cart.interface';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';
import { CartItem } from '../../interfaces/Cart-Interface/cart-item.interface';
import { UpdateCartItem } from '../../interfaces/Cart-Interface/update-cart-item.interface';
import { CookieService } from '../cookie-service/cookie.service';
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


  constructor(
    private http: HttpClient,
    private cookie: CookieService) {
  }

  getUser(id: number): Observable<any> {

    return this.http.get<UserProfile>(this.userURL + "/" + id + "/profile", {withCredentials:true});
  }

  getUserActiveCart(id:number): Observable<any> {
    return this.http.get<Cart>(this.userURL + "/" + id + "/cart", {withCredentials:true});
  }

  updateCartItem(cartItem: UpdateCartItem): Observable<any> {
    console.log(`${this.userURL}/cart/update`)
    console.log(cartItem);
    this.cookie.getCookie('user_session');
    return this.http.put<UpdateCartItem>(`${this.userURL}/cart/update`, cartItem, {withCredentials:true});
  }

  updateUserAddress(address : UserAddress) : Observable<any> {
    return this.http.put<UserAddress>(this.userURL + "/update/address", {withCredentials : true});
  }

  updateUserPayment(payment : UserPayment): Observable<any> {
    this.cookie.getCookie('user_session');
    return this.http.put<UserPayment>(`${this.userURL}/update/payment`, {withCredentials:true});
  }
}