import { Component, OnInit } from '@angular/core';
import { category } from 'src/app/shared/interfaces/Product-Interface/category-interface';
import { onSale } from 'src/app/shared/interfaces/Product-Interface/onsale-interface';
import { CreateProduct } from 'src/app/shared/interfaces/Product-Interface/product-create.interface';
import { Product } from 'src/app/shared/interfaces/Product-Interface/product.interface';
import { ProductService } from 'src/app/shared/services/product-service/product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss']
})
export class UpdateProductComponent implements OnInit {

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
  }

  category: category = {
    id: 1,
    name: "Test Act",
    description: "Test"
  }


  sale: onSale = {
    id: 1,
    discount: .25
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

  create: Product = {

    netPrice: 55,
    name: "",
    descr: "",
    price: 0,
    onSale: this.sale,
    category: this.category,
    isFeatured: false,

  }

  productById: Product = {
    netPrice: 0,
    id: 0,
    name: "",
    descr: "",
    price: 0,
    onSale: this.sale,
    category: this.category,
    isFeatured: false,
    //"image":

  }

  id: number = 0; 


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


  updateProduct(update: Product) {
    this.productService.update(update).subscribe({
    });
  }
}
