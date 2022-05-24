import { Component, OnInit } from '@angular/core';
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

  userId: number = 0;

  constructor(
    private userService: UserService,
    public cookie:CookieService
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

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  cartArray: Cart[] = [];
  cartTotal: number[] = [];

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
