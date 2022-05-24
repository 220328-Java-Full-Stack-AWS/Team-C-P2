import { Component, OnInit } from '@angular/core';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
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
    this.userService.getCurrentUser().subscribe(currentUser => {
      this.userInfo = currentUser;
    });
    this.productService.getAllFeatured().subscribe({
      next: response => {
        this.products = (response as any).data;
      },
      error: err => {
        // Todo: Handle error
      }
    });
  }

  addToCart(item: Product, event: Event): void {
    event.stopPropagation();
    this.userService.addCartItem(item.product);

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
