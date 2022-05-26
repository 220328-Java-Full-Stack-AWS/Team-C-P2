import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';
import { Order } from 'src/app/shared/interfaces/Order-Interface/order.interface';
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

  cartArray: Cart[] = [];

  constructor(
    private cookieService:CookieService,
    private userService: UserService,
    private route: ActivatedRoute,
    ){}

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  subscriber: any = null;
  id: any = "";

  order: Order = {
    cart: undefined,
    dateCreated: "",
    id: 0
  }

  getTax() {
    return this.order.cart!.total * .13;
  }


  ngOnInit(): void {
    this.cookieService.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));
    
    // Initialize current cart
    this.userService.getCurrentCartSubject().subscribe((res: any) => {
      this.cart = res;
    });

    this.subscriber = this.route.paramMap.subscribe(params => {
      this.id = params.get("id");
      this.userService.getOrderById(this.id).subscribe({
        next: response => {
          console.log(response)
          this.order = (response as any)
          console.log(this.order);
          this.userService.getCartById(this.order.cart!.id).subscribe((result: any) => {
            console.log(result);
            this.cartArray = result.data;
            console.log(this.cartArray);
          })
        },
        error: err => {
          // Todo: Handle error
        }
      })
    })
    }

    
}
