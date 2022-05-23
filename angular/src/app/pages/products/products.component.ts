import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/shared/services/product-service/product.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { category } from 'src/app/shared/interfaces/Product-Interface/category.interface';
import { onSale } from 'src/app/shared/interfaces/Product-Interface/onsale.interface';
import { CreateProduct } from 'src/app/shared/interfaces/Product-Interface/product-create.interface';
import { CartItem } from 'src/app/shared/interfaces/Cart-Interface/cart-item.interface';
import { UserService } from 'src/app/shared/services/user-service/user.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {


  products: Product[] = [];
  //Adding parameter value to extarct parameter 
  sub: any = null;
  category: any ={
    1: "Soccer",
    2: "Football",
    3: "Tenniss",
    4: "Test"
  }

  id: any = "";
  constructor(private productService: ProductService, private userService: UserService, private route: ActivatedRoute) { }
  //
  ngOnInit(): void {
    // let id: any;
    this.sub = this.route.paramMap.subscribe(params => {
      this.id = params.get("id");
      this.productService.getByCategoryId(this.id).subscribe({
        next: response => {
          console.log(response)
          this.products = (response as any).data;
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
    console.log(`Added ${item.product.name} to the cart`);
  }

}

interface Product {
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
