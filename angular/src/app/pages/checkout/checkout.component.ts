import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/shared/interfaces/Cart-Interface/cart-item.interface';
import { Router } from '@angular/router';
import { ActiveCart } from 'src/app/shared/interfaces/Cart-Interface/active-cart.interface';

import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';

import { UserProfile } from 'src/app/shared/interfaces/User-Interface/user-profile.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';
import { UpdateCartItem } from 'src/app/shared/interfaces/Cart-Interface/update-cart-item.interface';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  providers: [DatePipe]
})
export class CheckoutComponent implements OnInit {

  error?: string = "Error.";

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

  itemCount:number = 0;

  constructor(
    private userService: UserService,
    public cookie:CookieService,
    private datePipe: DatePipe,
    private router: Router
    ) {
     }

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
      let count = 0;
      currentCart.forEach((cart: Cart) => {
        totalCount += cart.cartItem?.netPrice! * cart.cartItem?.quantity!;
        count += cart.cartItem ? Number(cart.cartItem?.quantity!) : Number(cart.cartItem!.quantity);
      });
      this.itemCount = count;
      this.cartTotal = totalCount;
      this.cartArray.sort((a,b) => (a.cartItem?.id || 0) < (b.cartItem?.id || 0) ? -1 : 1);
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

  updateCartItem: UpdateCartItem = {
    cartItemId: 0,
    quantity: 0
  }

  myDate = new Date();

  activeCart: ActiveCart = {
    id: 0,
    user: this.userQuery,
    total: 0
  }

  submit(total: any){
    this.getUserById(this.user.userId);
    let currentDate = this.datePipe.transform(this.myDate, 'yyyy-MM-dd');
    console.log(this.userQuery.userAddress)
    console.log(this.userQuery.payment)
    if (this.userQuery.userAddress.userId == undefined || this.userQuery.payment.userId == undefined) {
      this.showError("Please ensure you have provided address and payment information.");

    }
    else {
      console.log("Current Date:" + currentDate);
      this.createOrder(this.userQuery, currentDate, total);
    }

  }

  createOrder(user: UserProfile, dateCreated: any, total:any) {
    this.activeCart.id = user.activeCartId;
    this.activeCart.user = user;
    this.activeCart.total = total;
    console.log("Current cart:" + this.activeCart);
    this.userService.createOrder(this.activeCart, dateCreated).subscribe((json:any) => {
      console.log(json);
      console.log(json.id);
      this.userService.setCartValue([]);
      this.router.navigate(['cart/checkout/order/' + json.id]);
    })
  }

  // function to close error window
  closeError(): void {
    const el = document.querySelector(".error") as HTMLElement;

    if(!el.classList.contains("hide")){
      el.classList.add("hide");
      this.error = "";
    }
  }

  // function to show errors
  showError(errorMessage: string): void {
    const el = document.querySelector('.error') as HTMLElement;

    this.closeError();
    if(el.classList.contains("hide")) {
      this.error = errorMessage;
      el.classList.remove("hide");
    }
  }
}
