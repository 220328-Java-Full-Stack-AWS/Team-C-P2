import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
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
    private sanitizer: DomSanitizer

  ) { }

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }
  cartArray: Cart[] = [];

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

  cartTotal: number = 0;
  
  getActiveCartByUserId(id:number) {
    this.userService.getUserActiveCart(id).subscribe((json: any) => {
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
        this.cartTotal += (e?.netPrice * e?.cartItem.quantity);
        this.cartArray.push(this.cartQuery);
      }
    })
  }

  updateCart(cartItemId: any, quantity: any){
    this.updateCartItem.cartItemId = cartItemId;
    this.updateCartItem.quantity = quantity;
    console.log(this.updateCartItem);
    this.userService.updateCartItem(this.updateCartItem).subscribe((json:any) => {
      console.log(json);
    });
  
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
    this.getActiveCartByUserId(this.user.userId);
  }

}
