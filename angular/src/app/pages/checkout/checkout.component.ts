import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/shared/interfaces/Cart-Interface/cart-item.interface';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';

import { UserProfile } from 'src/app/shared/interfaces/User-Interface/user-profile.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  quantity: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  cartArray: Cart[] = [];
  cartTotal: number = 0;

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

  constructor(
    private userService: UserService,
    public cookie:CookieService)
  {}

  ngOnInit(): void {
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));
    this.getUserById(this.user.userId);

    // Initialize current cart
    this.userService.getCurrentCartSubject().subscribe((currentCart: Cart[]) => {
      this.cartArray = currentCart;
      // Initialize cart total (count and set view)
      let totalCount = 0;
      currentCart.forEach((cart: Cart) => {
        totalCount += cart.netPrice!;
      });
      this.cartTotal = totalCount;
    });
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

  updateCart(cartItem: CartItem, quantity: any){
    this.userService.updateItemInCart(cartItem, quantity);
  }

  removeCartItem(cartItem: CartItem) {
    this.userService.removeCartItem(cartItem);
  }

  calculateTax(total:number){
    return (total * .13);
  }
}
