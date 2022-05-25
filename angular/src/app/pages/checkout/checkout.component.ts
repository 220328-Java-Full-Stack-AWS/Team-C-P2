import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveCart } from 'src/app/shared/interfaces/Cart-Interface/active-cart.interface';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { UpdateCartItem } from 'src/app/shared/interfaces/Cart-Interface/update-cart-item.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';

import { UserProfile } from 'src/app/shared/interfaces/User-Interface/user-profile.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  providers: [DatePipe]
})
export class CheckoutComponent implements OnInit {

  userId: number = 0;

  constructor(
    private userService: UserService,
    public cookie:CookieService,
    private datePipe: DatePipe,
    private router: Router
    ) {
     }

  userQuery: UserProfile = {
    id: 0,
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    role: "",
    activeCartId: 0,
    userAddress: {
      userId: 0,
      addressLine1: "",
      addressLine2: "string",
      city: "",
      postalCode: 0,
      country: "",
      phoneNumber: ""
    },
    payment: {
      userId: 0,
      network: "",
      issuer: "",
      cardNumber: "",
      securityCode: 0,
      expDate: ""
    },
    dateCreated: "",
    dateModifies: ""
  }

  getUserById(id:number) {
    this.userService.getUser(id).subscribe((json: any) => {
      console.log(json);
      this.userQuery = {
        id: json.data.id,
        username: json.data?.username,
        firstName: json.data?.firstName,
        lastName: json.data?.lastName,
        email: json.data?.email,
        role: json.data?.role,
        activeCartId: json.data?.activeCartId,
        userAddress: {
          userId: json.data.userAddress?.id,
          addressLine1: json.data.userAddress?.addressLine1,
          addressLine2: json.data.userAddress?.addressLine2,
          city: json.data.userAddress?.city,
          postalCode: json.data.userAddress?.postalCode,
          country: json.data.userAddress?.country,
          phoneNumber: json.data.userAddress?.phoneNumber
        },
        payment: {
          userId: json.data.payment?.id,
          network: json.data.payment?.network,
          issuer: json.data.payment?.issuer,
          cardNumber: json.data.payment?.cardNumber,
          securityCode: json.data.payment?.securityCode,
          expDate: json.data.payment?.expirationDate
        },
        dateCreated: json.data?.dateCreated,
        dateModifies: json.data?.dateModifies
      }
    })
  }

  updateCart(cartItemId: any, quantity: any){
    this.updateCartItem.cartItemId = cartItemId;
    this.updateCartItem.quantity = quantity;
    console.log(this.updateCartItem);
    this.userService.updateCartItem(this.updateCartItem).subscribe((json:any) => {
      console.log(json);
      this.cartArray = [];
      this.cartTotal = [];
      this.userService.setCartTotal(this.cartTotal);
      this.ngOnInit();
    });
  }

  removeCartItem(id: any) {
    this.userService.removeCartItem(id).subscribe((json:any) => {
      console.log(json);
      this.ngOnInit();
    })
  }

  calculateTax(total:number){
    return (total * .13);
  }

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  updateCartItem: UpdateCartItem = {
    cartItemId: 0,
    quantity: 0
  }

  quantity: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  cartArray: Cart[] = [];
  cartTotal: number[] = [];

  myDate = new Date();

  activeCart: ActiveCart = {
    id: 0,
    user: this.userQuery,
    total: 0
  }

  submit(total: any){
    this.getUserById(this.user.userId);
    let currentDate = this.datePipe.transform(this.myDate, 'yyyy-MM-dd');
    console.log("Current Date:" + currentDate);
    this.createOrder(this.userQuery, currentDate, total);
    
  }

  createOrder(user: UserProfile, dateCreated: any, total:any) {
    this.activeCart.id = user.activeCartId;
    this.activeCart.user = user;
    this.activeCart.total = total;
    console.log("Current cart:" + this.activeCart);
    this.userService.createOrder(this.activeCart, dateCreated).subscribe((json:any) => {
      console.log(json);
      console.log(json.id);
      this.router.navigate(['cart/checkout/order/' + json.id]);
    })
  }

  ngOnInit(): void {
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));
    this.getUserById(this.user.userId);
    this.userService.getCurrentActiveCart().subscribe((cartArray) => (
      this.cartArray = cartArray
    ));
    this.userService.getCartTotal().subscribe((cartTotal) => (
      this.cartTotal = cartTotal
    ));
  }

}
