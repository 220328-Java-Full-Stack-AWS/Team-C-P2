import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/shared/interfaces/Cart-Interface/cart-item.interface';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  currentCart: Cart[] = [];
  cartTotal: number = 0;
  quantity: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  itemCount: number = 0;

  constructor(
    private userService: UserService,
    private cookie: CookieService,
  ) { }

  updateCart(cartItem: CartItem, quantity: any){
    this.userService.updateItemInCart(cartItem, quantity);
    
  }

  removeCartItem(cartItem: CartItem) {
    this.userService.removeCartItem(cartItem);
  }



  ngOnInit(): void {
    this.cookie.getCookie('user_session');
    // Initialize current cart
    this.userService.getCurrentCartSubject().subscribe((res: any) => {
      this.currentCart = res;
      // Initialize cart total (count and set view)
      let totalCount = 0;
      let count = 0;
      Array.prototype.forEach.call(this.currentCart, (cart: Cart) => {
        totalCount += cart.cartItem?.netPrice! * cart.cartItem?.quantity!;
        count += cart.cartItem ? Number(cart.cartItem?.quantity!) : Number(cart.cartItem!.quantity);
      });
      this.cartTotal = totalCount;
      this.itemCount = count;
      this.currentCart.sort((a,b) => (a.cartItem?.id || 0) < (b.cartItem?.id || 0) ? -1 : 1);
    });

  }
}

