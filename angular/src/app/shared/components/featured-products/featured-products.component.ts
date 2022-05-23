import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { Observable, of } from 'rxjs';
import { UserService } from '../../services/user-service/user.service';
import { ProductService } from '../../services/product-service/product.service';
import { onSale } from '../../interfaces/Product-Interface/onsale.interface';
import { category } from '../../interfaces/Product-Interface/category.interface';

@Component({
  selector: 'app-featured-products',
  templateUrl: './featured-products.component.html',
  styleUrls: ['./featured-products.component.scss']
})
export class FeaturedProductsComponent implements OnInit {

  products: Product[] = [];
  userInfo?: UserInfo;

  constructor(private productService: ProductService, private userService: UserService) { }

  ngOnInit(): void {
    this.productService.getAllFeatured().subscribe({
      next: response => {
        this.products = (response as any).data;
        console.log(this.products);
      },
      error: err => {
        // Todo: Handle error
      }
    });
  }

  addToCheckout(item: Product, event: Event): void {
    // Todo: Add item to users cart
    console.log(event);
    (event.target as HTMLElement).classList.add('inCart');
    console.log(`Added ${item.product.name} to the cart`);
  }

}
// export interface Product {
//   netPrice: number,
//   id?: number,
//   name: string,
//   descr: string,
//   price: number,
//   onSale: onSale,
//   category: category,
//   isFeatured: boolean
//   image?: Blob
// }
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
