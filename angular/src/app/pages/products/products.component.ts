import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/shared/interfaces/Product-Interface/product.interface';
import { ProductService } from 'src/app/shared/services/product-service/product.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateProduct } from 'src/app/shared/interfaces/Product-Interface/product-create.interface';
import { category } from 'src/app/shared/interfaces/Product-Interface/category-interface';
import { onSale } from 'src/app/shared/interfaces/Product-Interface/onsale-interface';



@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  id: number = 0;

  price: number = 0;
  category: category = {
    id: 1,
    name: "Test Act",
    description: "Test"
  }


  sale: onSale = {
    id: 1,
    discount: .25
  }

  productById: Product = {
    netPrice: 0,
    id: 6,
    name: "",
    descr: "",
    price: 0,
    onSale: this.sale,
    category: this.category,
    isFeatured: false,
    //"image":

  }


  product: any = {
    id: 0,
    netPrice: 55,
    name: "",
    descr: "",
    price: 55,
    onSale: {
      id: 1,
      discount: .25
    },
    category: this.category,
    isFeatured: false,
    //"image":

  }


  create: CreateProduct = {

    netPrice: 55,
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

  createProduct(createProduct: CreateProduct) {
    this.productService.create(createProduct).subscribe({

    });
    console.log(createProduct)
  }

  updatedProduct: Product = {
    id: 1,
    netPrice: .99,
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


  constructor(private productService: ProductService) { }

  ngOnInit(): void {

  }

}
