import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { UpdateCartItem } from 'src/app/shared/interfaces/Cart-Interface/update-cart-item.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  constructor(
    private userService: UserService,
    private cookie: CookieService,
    private sanitizer: DomSanitizer,
    private router: Router

  ) { }

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }
  cartArray: Cart[] = [];

  cartTotal: number[] = [];


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
    })
  }

  updateCartItem: UpdateCartItem = {
    cartItemId: 0,
    quantity: 0
  }

  quantity: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  ngOnInit(): void {
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));
    this.userService.getCurrentActiveCart().subscribe((cartArray) => (
      this.cartArray = cartArray
    ));
    this.userService.getCartTotal().subscribe((cartTotal) => (
      this.cartTotal = cartTotal
    ));
  }
}

