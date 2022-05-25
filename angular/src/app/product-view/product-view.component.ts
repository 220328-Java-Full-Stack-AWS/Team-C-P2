import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from '../shared/interfaces/Cart-Interface/cart-item.interface';
import { category } from '../shared/interfaces/Product-Interface/category.interface';
import { onSale } from '../shared/interfaces/Product-Interface/onsale.interface';
import { CookieService } from '../shared/services/cookie-service/cookie.service';
import { ProductService } from '../shared/services/product-service/product.service';
import { UserService } from '../shared/services/user-service/user.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.scss']
})
export class ProductViewComponent implements OnInit {
  products: Item[] = [];
  quantity: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  isLoggedIn: boolean = false;

  error?: string = "Error.";

  productById: Item = {
    netPrice: 0,
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
        name: "",
        description: "",
        image: undefined
      },
      isFeatured: false,
      image: undefined
    }
  }
  //Adding parameter value to extarct parameter 
  sub: any = null;


  id: any = "";
  constructor(
    private productService: ProductService
    , private userService: UserService,
    private route: ActivatedRoute,
    private cookieService: CookieService) { }
  //
  ngOnInit(): void {
    // let id: any;
    this.sub = this.route.paramMap.subscribe(params => {
      this.id = params.get("id");
      this.productService.getProductById(this.id).subscribe({
        next: response => {
          console.log(response)
          this.productById = (response as any).data;
        },
        error: err => {
          // Todo: Handle error
        }
      });
    });

    if (this.cookieService.getCookie('user_session')) {
      this.isLoggedIn = true;
    }
    else {
      this.isLoggedIn = false;
    }
  }

  addToCart(item: Item, event: Event, qty: any): void {
    event.stopPropagation();
    this.userService.addToCart(item.product, qty);
    (event.target as HTMLElement).classList.add('inCart');

  }

  // function to close error window
  closeError(): void {
    const el = document.querySelector(".error") as HTMLElement;

    if (!el.classList.contains("hide")) {
      el.classList.add("hide");
      this.error = "";
    }
  }

  // function to show errors
  showError(errorMessage: string): void {
    const el = document.querySelector('.error') as HTMLElement;

    this.closeError();
    if (el.classList.contains("hide")) {
      this.error = errorMessage;
      el.classList.remove("hide");
    }
  }

}


interface Item {
  netPrice: number,
  product: {
    id?: number,
    name: string,
    descr: string,
    price: number,
    onSale: onSale,
    category: category,
    isFeatured: boolean
    image?: Blob
  }
}