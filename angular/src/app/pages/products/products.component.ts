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



@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {


  products: Product[] = [];
  //Adding parameter value to extarct parameter
  sub: any = null;
  category: any = {
    1: "Soccer",
    2: "Football",
    3: "Tennis",
    4: "Test"
  }
  categories: any;

  sale: onSale = {
    id: 1,
    discount: .25
  }

  productById: Product = {
    id: 0,
    name: "",
    descr: "",
    price: 0,
    onSale: this.sale,
    category: this.category,
    isFeatured: false,
    //"image":

  }

  create: CreateProduct = {

    name: "",
    descr: "",
    price: 55,
    onSale: this.sale,
    category: this.category,
    isFeatured: false,
    //"image":

  }

  getAllProducts: Array<Product> = [];



  getAll() {
    this.productService.getAllProducts()
      .subscribe((data: any) => {

        for (var index in data) {
          console.log(data[index])
          this.getAllProducts.push(data[index]);
        }

      })

  }



  getByCategory(id: number) {
    this.productService.getByCategoryId(id)
      .subscribe((data: any) => {

        for (var index in data) {
          console.log(data[index])
          this.getAllProducts.push(data[index]);
        }

      })

  }


  getAllFeatured() {
    this.productService.getAllFeatured()
      .subscribe((data: any) => {

        for (var index in data) {
          this.getAllProducts.push(data[index]);
        }

      })

  }



  getProductPrice(id: number) {

    this.productService.getProductPrice(id)
      .subscribe((data: any) => {
        console.log(data)
        // this.price = data.data
      })

  }

  createProduct(createProduct: CreateProduct) {
    this.productService.create(createProduct).subscribe({

    });
    console.log(createProduct)
  }

  updatedProduct: Product = {
    id: 1,
    name: "Upadting from Front End",
    descr: "Updated",
    price: .99,
    onSale: this.sale,
    category: this.category,
    isFeatured: false,
    //"image":

  }

  updateProduct(update: Product) {
    this.productService.update(update).subscribe({
    });
  }


  deleteByID(id: number) {
    this.productService.deleteById(id).subscribe({

    });
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
