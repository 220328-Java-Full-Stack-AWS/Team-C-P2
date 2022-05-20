import { identifierName } from '@angular/compiler';
import { decimalDigest } from '@angular/compiler/src/i18n/digest';
import { Component, OnInit } from '@angular/core';
import { flush } from '@angular/core/testing';
import { Data } from '@angular/router';
import { Product } from 'src/app/shared/interfaces/Product-Interface/product.interface';
import { ProductService } from 'src/app/shared/services/product-service/product.service';



@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  id: number = 0;

  price: number = 0;

  result: Product = {
    // netPrice: 0,

    id: 0,
    name: "",
    descr: "",
    price: 0,
    // onSale: {
    //   id: 0,
    //   discount: 0
    // },
    category: 0,
    isFeatured: false,
    //"image":

  }

  getAll() {
    this.productService.getAllProducts()
      .subscribe((data: any) => {
        console.log(data.data.product)
        this.result = {

          // netPrice: data.netPrice,

          "id": data.id,
          "name": data.name,
          "descr": data.descr,
          "price": data.price,
          // "onSale": {
          //   "id": data.onSale.id,
          //   "discount": data.onSale.discount
          // },
          "category": data.category,
          "isFeatured": data.isFeatured,
          //"image": 
        }

      })
    console.log(this.result)
  }

  getByID(id: number) {

    this.productService.getProductById(id)
      .subscribe((data: any) => {
        // var test1: any = data.netPrice.map((d: any) => d.netPrice);
        // console.log("Data" + data.data.product.id)
        this.result = {
          // netPrice: data.netPrice,

          id: data.data.product.id,
          name: data.data.product.name,
          descr: data.data.product.descr,
          price: data.data.product.price,
          // onSale: {
          //   id: data.id,
          //   discount: data.onSale.discount
          // },
          category: data.data.product.category,
          isFeatured: data.data.product.isFeatured,
          //"image": 
        }

      })
    console.log(this.result.id)
  }

  getProductPrice(id: number) {
    this.productService.getProductPrice(id)
      .subscribe((data: any) => {
        console.log(data)
        this.price = data.netPrice

      })
    console.log("Price" + this.price)
  }

  constructor(private productService: ProductService) { }

  ngOnInit(): void {

  }

}
