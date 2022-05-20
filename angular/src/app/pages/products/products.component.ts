import { identifierName } from '@angular/compiler';
import { decimalDigest } from '@angular/compiler/src/i18n/digest';
import { ANALYZE_FOR_ENTRY_COMPONENTS, Component, OnInit } from '@angular/core';
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

  productById: Product = {
    netPrice: 0,
    id: 0,
    name: "",
    descr: "",
    price: 0,
    onSale: {
      id: 0,
      discount: 0
    },
    category: 0,
    isFeatured: false,
    //"image":

  }

  getAllProducts: Array<Product> = [];



  getAll() {
    this.productService.getAllProducts()
      .subscribe((data: any) => {

        for (var index in data) {
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


  getByID(id: number) {

    this.productService.getProductById(id)
      .subscribe((data: any) => {
        // var test1: any = data.netPrice.map((d: any) => d.netPrice);
        // console.log("Data" + data.data.product.id)
        this.productById = {
          netPrice: data.data.netPrice,

          id: data.data.product.id,
          name: data.data.product.name,
          descr: data.data.product.descr,
          price: data.data.product.price,
          onSale: {
            id: data.data.product.onSale.id,
            discount: data.data.product.onSale.discount
          },
          category: data.data.product.category,
          isFeatured: data.data.product.isFeatured,
          //"image": 
        }

      })
    console.log("Data" + this.productById)
  }

  getProductPrice(id: number) {

    this.productService.getProductPrice(id)
      .subscribe((data: any) => {
        console.log(data)
        this.price = data.data
      })

  }

  constructor(private productService: ProductService) { }

  ngOnInit(): void {

  }

}
