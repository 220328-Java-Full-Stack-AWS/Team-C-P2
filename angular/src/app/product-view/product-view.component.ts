import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from '../shared/interfaces/Cart-Interface/cart-item.interface';
import { category } from '../shared/interfaces/Product-Interface/category.interface';
import { onSale } from '../shared/interfaces/Product-Interface/onsale.interface';
import { Product } from '../shared/interfaces/Product-Interface/product.interface';
import { ProductService } from '../shared/services/product-service/product.service';
import { UserService } from '../shared/services/user-service/user.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.scss']
})
export class ProductViewComponent implements OnInit {

  productById: Product = {
    id: 0,
    name: "",
    descr: "",
    price: 0,
    onSale: {
      id: 0,
      discount: 0,
    },
    category: {
      id: 0,
      name: "",
      description: "",
      image: undefined
    },
    isFeatured: false,

  }
  //Adding parameter value to extarct parameter 
  sub: any = null;


  id: any = "";
  constructor(private productService: ProductService, private userService: UserService, private route: ActivatedRoute) { }
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
  }

  addToCart(item: Product, event: Event): void {
    let cartItem: CartItem;

    // this.userService.

    // Todo: Add item to users cart
    (event.target as HTMLElement).classList.add('inCart');
    console.log(`Added ${item.name} to the cart`);
  }

}


