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
import { Product } from 'src/app/shared/interfaces/Product-Interface/product.interface';
import { FeaturedProductsComponent } from 'src/app/shared/components/featured-products/featured-products.component';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { Cart } from 'src/app/shared/interfaces/Cart-Interface/cart.interface';



@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  constructor(
    private productService: ProductService, 
    private userService: UserService, 
    private route: ActivatedRoute,
    private featuredInj: FeaturedProductsComponent
    ) { }

  products: Item[] = [];
  //Adding parameter value to extarct parameter
  sub: any = null;

  userInfo: UserInfo = {
    userId: 0,
    activeCartId: 0
  }
  quantity: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  getAllProducts: Array<Product> = [];

  cartArray: Cart[] = [];

  getByCategory(id: number) {

    this.userService.getCurrentUser().subscribe(currentUser => {
      this.userInfo = currentUser;
    });
    this.userService.getCurrentActiveCart().subscribe((cartArray) => (
      this.cartArray = cartArray
    ));

    this.productService.getByCategoryId(id)
      .subscribe((data: any) => {

        for (var index in data) {
          console.log(data[index])
          this.getAllProducts.push(data[index]);
        }

      })

  }



  id: any = "";
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
          console.error(err);
        }
      });
    });




  }

  addToCart(item: Item, event: Event): void {
    let cartItem: CartItem;

    // this.userService.

    // Todo: Add item to users cart
    (event.target as HTMLElement).classList.add('inCart');
    console.log(`Added ${item.product.name} to the cart`);
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
