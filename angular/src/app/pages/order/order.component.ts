import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  cart: Cart[] = [];

  constructor(
    private cookieService:CookieService,
    private userService: UserService,
    ){}

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  ngOnInit(): void {
    this.cookieService.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));

    this.userService.getCurrentActiveCartLength().subscribe((cartArray) => (
      this.cart = cartArray
      ));
    }
}
