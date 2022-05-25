import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { Cart } from '../../interfaces/Cart-Interface/cart.interface';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';
import { CartItem } from '../../interfaces/Cart-Interface/cart-item.interface';
import { CookieService } from '../cookie-service/cookie.service';
import { Product } from '../../interfaces/Product-Interface/product.interface';
import { UserAddress } from '../../interfaces/user-address.interface';
import { UserPayment } from '../../interfaces/user-payment.interface';
import { PasswordToChange } from '../../interfaces/password-to-change.interface';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  userURL: string = "http://localhost:8080/user";

  private user: UserInfo; // Contains user
  private currentCart: Cart[] = [];
  private userSubject: BehaviorSubject<UserInfo>;
  private currentCartSubject: BehaviorSubject<Cart[]> = new BehaviorSubject<Cart[]>(this.currentCart);

  // length: Cart[] = [];
  // cartArray: Cart[] = [];
  // num: number = 0;
  // total: number[] = [];

  // private cartLength: Cart[] = this.length;
  // private cartItems: Cart[] = this.cartArray;
  // private totalNum: number[] = this.total;

  constructor(
    private http: HttpClient,
    private cookie: CookieService) {
      // Initialize User
      this.user = JSON.parse(localStorage.getItem('userInfo') || '{}');
      this.userSubject = new BehaviorSubject<UserInfo>(this.user);
      this.getUserActiveCart().subscribe((res: any) => {
        // Initialize Cart
        this.currentCart = res.data;
        this.currentCartSubject.next(this.currentCart);

        console.log(this.currentCart);
      });
  }

  /**
   * Return current cart subject to be subscribed to
   */
  getCurrentCartSubject(): BehaviorSubject<Cart[]> {
    console.log(this.currentCartSubject);
    return this.currentCartSubject;
  }


  /**
   * Retrieves cart from database
   */
  getUserActiveCart(): Observable<any> {
    return this.http.get<any>(this.userURL + "/" + this.user.userId + "/cart", { withCredentials:true });
  }



  // private currentUserSubject: BehaviorSubject<UserInfo> = new BehaviorSubject(
  //   this.user
  // );

  // private cartTotal: BehaviorSubject<number[]> = new BehaviorSubject(
  //   this.totalNum
  // );

  // private currentCartItems: BehaviorSubject<Cart[]> = new BehaviorSubject(
  //   this.cartItems
  // );

  // private currentCartLength: BehaviorSubject<Cart[]> = new BehaviorSubject(
  //   this.cartLength
  // );


  // cartQuery: Cart = {
  //   cartItem: {
  //     id: 0,
  //     cartId: 0,
  //     product: {
  //       id: 0,
  //       name: "",
  //       descr: "",
  //       price: 0,
  //       onSale: {
  //         id: 0,
  //         discount: 0
  //       },
  //       category: {
  //         id: 0,
  //         description: "",
  //         name: "",
  //         image: undefined,
  //       },
  //       isFeatured: false,
  //       image: undefined,
  //     },
  //     quantity: 0,
  //     netPrice: 0,
  //   },
  //   netPrice: 0
  // };



  // getCurrentActiveCartLength(): BehaviorSubject<Cart[]> {
  //   this.length = []
  //   this.getCartLength(this.user.userId);
  //   this.currentCartLength.next(this.length);
  //   return this.currentCartLength;
  // }

  // // getCLength(): BehaviorSubject<Cart[]> {
  // //   return this.currentCartLength;
  // // }

  // getCartLength(id:number) {
  //   this.getUserActiveCart(id).subscribe((json: any) => {
  //     console.log(json);
  //     // Get cart
  //     for (let e of json.data) {
  //       this.cartQuery = {
  //         cartItem: {
  //           id: e.cartItem?.id,
  //           cartId: e.cartItem?.cartId,
  //           product: {
  //             id: e.cartItem?.product.id,
  //             name: e.cartItem?.product.name,
  //             descr: e.cartItem?.product.descr,
  //             price: e.cartItem?.product.price,
  //             onSale: {
  //               id: e.product?.onSale.id,
  //               discount: e.product?.onSale.discount
  //             },
  //             category: {
  //               id: e.product?.category.id,
  //               description: e.product?.category.description,
  //               name: e.product?.category.name,
  //               image: e.product?.category.image,
  //             },
  //             isFeatured: e.cartItem?.product.isFeatured,
  //             image: e.cartItem?.product.image,
  //           },
  //           quantity: e.cartItem?.quantity,
  //           netPrice: e.cartItem?.netPrice,
  //         },
  //         netPrice: e?.netPrice
  //       }
  //       // Adding to length
  //       this.length.push(this.cartQuery);
  //     }
  //   })
  // }

  // getActiveCartByUserId(id:number) {
  //   this.getUserActiveCart(id).subscribe((json: any) => {
  //     console.log(json);
  //     for (let e of json.data) {
  //       this.cartQuery = {
  //         cartItem: {
  //           id: e.cartItem?.id,
  //           cartId: e.cartItem?.cartId,
  //           product: {
  //             id: e.cartItem?.product.id,
  //             name: e.cartItem?.product.name,
  //             descr: e.cartItem?.product.descr,
  //             price: e.cartItem?.product.price,
  //             onSale: {
  //               id: e.product?.onSale.id,
  //               discount: e.product?.onSale.discount
  //             },
  //             category: {
  //               id: e.product?.category.id,
  //               description: e.product?.category.description,
  //               name: e.product?.category.name,
  //               image: e.product?.category.image,
  //             },
  //             isFeatured: e.cartItem?.product.isFeatured,
  //             image: e.cartItem?.product.image,
  //           },
  //           quantity: e.cartItem?.quantity,
  //           netPrice: e.cartItem?.netPrice,
  //         },
  //         netPrice: e?.netPrice
  //       }
  //       this.cartArray.push(this.cartQuery);
  //     }
  //   })
  // }

  // getTotalByUserId(id:number) {
  //   this.getUserActiveCart(id).subscribe((json: any) => {
  //     for (let e of json.data) {
  //       this.num += (e?.netPrice * e?.cartItem.quantity);
  //     }
  //     this.total.push(this.num);
  //   })
  // }

  // getCartTotal(): BehaviorSubject<number[]> {
  //   this.total = [];
  //   this.num = 0;
  //   this.getTotalByUserId(this.user.userId);
  //   this.cartTotal.next(this.total);
  //   return this.cartTotal;
  // }

  // setCartTotal(num: number[]) {
  //   this.totalNum = num;
  // }

  /* ~~~~~~~~~~~~~~~~~~ Backwards compatibility ~~~~~~~~~~~~~~~~~~ */
  getCurrentUser(): BehaviorSubject<UserInfo> {
    return this.userSubject;
  }
  getUser(id: number): Observable<any> {
    return this.http.get<UserProfile>(this.userURL + "/" + this.user.userId + "/profile", {withCredentials:true});
  }
  getCurrentActiveCart(): BehaviorSubject<Cart[]> {
    return this.currentCartSubject;
  }
  /* ~~~~~~~~~~~~~~~~~~ Backwards compatibility ~~~~~~~~~~~~~~~~~~ */

  setUserId(userId:number) {
    this.user.userId = userId;
    this.updateUserLocalStorage();
    // Emit
    this.userSubject.next(this.user);
  }

  setActiveCartId(cartId:number) {
    this.user.activeCartId = cartId;
    this.updateUserLocalStorage();
    // Emit
    this.userSubject.next(this.user);
  }

  updateUserLocalStorage(): void {
    localStorage.setItem('userInfo', JSON.stringify(this.user));
  }





  // updateCartItem(cartItem: UpdateCartItem): Observable<any> {
  //   console.log(`${this.userURL}/cart/update`)
  //   console.log(cartItem);
  //   this.cookie.getCookie('user_session');
  //   return this.http.put<UpdateCartItem>(`${this.userURL}/cart/update`, cartItem, {withCredentials:true});
  // }

  updateItemInCart(cartItem: CartItem, qty: number) {

    console.log("INSIDE UPDATEITEMINCART");
    console.log(cartItem);
    const itemToUpdate: CartItemUpdateDto = {
      cartItemId: cartItem.id!,
      quantity: qty
    }

    this.http.put(`${this.userURL}/cart/update`, itemToUpdate, { withCredentials: true }).subscribe({
          next: response => {

            //Successfully Updated in backend
            console.log("Successfully updated item")
            console.log(response);
            // Loop update in memory cart to update it
            Array.prototype.forEach.call(this.currentCart, (cart: Cart) => {
              if(cart.cartItem?.id == cartItem.id){
                cart.cartItem = cartItem;
              }
            });
            // Emit
            this.currentCartSubject.next(this.currentCart);
          },
          error: err => {
            console.error("Failed to update cart item");
          }
        });
  }

  removeCartItem(cartItem: CartItem): void {
    this.http.delete(`${this.userURL}/cart/remove/` + cartItem.id, { withCredentials:true }).subscribe( () => {
      // Successfully deleted cart item
      Array.prototype.forEach.call(this.currentCart, (cart: Cart) => {
        // Remove element from in Memory
        if(cartItem.id == cart.cartItem?.id){
          this.currentCart.splice(this.currentCart.indexOf(cartItem), 1);
          // Emit
          this.currentCartSubject.next(this.currentCart);
        }
      });
    });
  }

  addToCart(product: Product, qty: any): void {
    let isAlreadyAdded: boolean = false;

    Array.prototype.forEach.call(this.currentCart, (cart: Cart) => {
      // Item already exists in cart
      console.log(cart.cartItem?.product?.id + "==" + product.id)
      if (cart.cartItem?.product?.id == product.id) {
        console.log("found equal")
        console.log(cart);
        this.updateItemInCart(cart.cartItem!, Number(qty) + Number(cart.cartItem!.quantity));
        isAlreadyAdded = true;
        return;
      }
    });
    if(isAlreadyAdded)
      return;

    // Construct new cart
    const cart: Cart = {
      cartItem: {
        cartId: this.user.activeCartId,
        product: product,
        quantity: qty,
        netPrice: product.onSale != null ? product.price * product.onSale.discount! * qty : product.price * qty
      }
    }
    // Server side representation differs from client side
    const cartToPersist: CartItemDto = {
      userId: this.user.userId,
      productId: product.id!,
      quantity: qty
    };

    // Attempt to persist in database
    this.http.post(`${this.userURL}/cart/add`, cartToPersist, { withCredentials: true }).subscribe((response: any) => {
      // Success
      //Successfully Updated in backend
      console.log("Successfully added item")
      console.log(response);
      cart.cartItem!.id = response.data.id;
      // Update in memory
      Array.prototype.push.call(this.currentCart, cart);
      // Emit
      this.currentCartSubject.next(this.currentCart);
    });
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

  updateUserPassword(password : PasswordToChange) : Observable<any>  {
    this.cookie.getCookie('user_session');
    // observe entire response
    return this.http.put<PasswordToChange>(`${this.userURL}/profile/update/password`, password, {withCredentials : true, observe : `response`});

  }
}

interface CartItemDto {
  userId: number,
  productId: number,
  quantity: number,
  netPrice?: number
}

interface CartItemUpdateDto {
  cartItemId: number,
  quantity: number
}
