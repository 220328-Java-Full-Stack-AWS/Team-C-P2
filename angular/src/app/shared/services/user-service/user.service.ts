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
import { ChangePassword } from '../../interfaces/change-password.interface';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  userURL: string = "http://localhost:8080/user";

  constructor(
    private http: HttpClient,
    private cookie: CookieService) {
  }

  cartArray: Cart[] = [];
  num: number = 0;
  total: number[] = [];

  private cartItems: Cart[] = this.cartArray;
  private totalNum: number[] = this.total;
  private user: UserInfo = JSON.parse(localStorage.getItem('userInfo') || '{}');

  private currentUserSubject: BehaviorSubject<UserInfo> = new BehaviorSubject(
    this.user
  );
  
  private cartTotal: BehaviorSubject<number[]> = new BehaviorSubject(
    this.totalNum
  );

  private currentCartItems: BehaviorSubject<Cart[]> = new BehaviorSubject(
    this.cartItems
  );
  

  cartQuery: Cart = {
    cartItem: {
      id: 0,
      cartId: 0,
      product: {
        id: 0,
        name: "",
        descr: "",
        price: 0,
        onSale: {
          id: 0,
          discount: 0
        },
        category: {
          id: 0,
          description: "",
          name: "",
          image: undefined,
        },
        isFeatured: false,
        image: undefined,
      },
      quantity: 0,
      netPrice: 0,
    },
    netPrice: 0
  };

  getCurrentActiveCart(): BehaviorSubject<Cart[]> {
    this.cartArray = []
    this.getActiveCartByUserId(this.user.userId);
    this.currentCartItems.next(this.cartArray);
    return this.currentCartItems;
  }

  getActiveCartByUserId(id:number) {
    this.getUserActiveCart(id).subscribe((json: any) => {
      console.log(json);
      for (let e of json.data) {
        this.cartQuery = {
          cartItem: {
            id: e.cartItem?.id,
            cartId: e.cartItem?.cartId,
            product: {
              id: e.cartItem?.product.id,
              name: e.cartItem?.product.name,
              descr: e.cartItem?.product.descr,
              price: e.cartItem?.product.price,
              onSale: {
                id: e.product?.onSale.id,
                discount: e.product?.onSale.discount
              },
              category: {
                id: e.product?.category.id,
                description: e.product?.category.description,
                name: e.product?.category.name,
                image: e.product?.category.image,
              },
              isFeatured: e.cartItem?.product.isFeatured,
              image: e.cartItem?.product.image,
            },
            quantity: e.cartItem?.quantity,
            netPrice: e.cartItem?.netPrice,
          },
          netPrice: e?.netPrice
        }
        this.cartArray.push(this.cartQuery);
      }
    })
  }

  getTotalByUserId(id:number) {
    this.getUserActiveCart(id).subscribe((json: any) => {
      for (let e of json.data) {
        this.num += (e?.netPrice * e?.cartItem.quantity);
      }
      this.total.push(this.num);
    })
  }


  getCartTotal(): BehaviorSubject<number[]> {
    this.total = [];
    this.num = 0;
    this.getTotalByUserId(this.user.userId);
    this.cartTotal.next(this.total);
    return this.cartTotal;
  }

  setCartTotal(num: number[]) {
    this.totalNum = num;
  }

  getCurrentUser(): BehaviorSubject<UserInfo> {

    return this.currentUserSubject;
  }

  setUserId(userId:number) {
    this.user.userId = userId;
  }

  setActiveCartId(cartId:number) {
    this.user.activeCartId = cartId;
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
    console.log(address);
    this.cookie.getCookie('user_session');
    return this.http.put<UserAddress>(`${this.userURL}/profile/update/address`, address, {withCredentials : true});
  }

  updateUserPayment(payment : UserPayment): Observable<any> {
    this.cookie.getCookie('user_session');
    return this.http.put<UserPayment>(`${this.userURL}/profile/update/payment`, payment, {withCredentials : true});
  }

  updateUserPassword(password : ChangePassword) : Observable<any>  {
    this.cookie.getCookie('user_session');
    // observe entire response
    return this.http.put<ChangePassword>(`${this.userURL}/profile/update/password`, password, {withCredentials : true, observe : `response`});

  }

  removeCartItem(id:number): Observable<any> {
    return this.http.delete(`${this.userURL}/cart/remove/` + id, {withCredentials:true});
  }
}