import { Component, OnInit } from '@angular/core';
import { CreateProduct } from 'src/app/shared/interfaces/Product-Interface/product-create.interface';
import { ProductService } from 'src/app/shared/services/product-service/product.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss']
})
export class CreateProductComponent implements OnInit {


  constructor(private productService: ProductService) { }



  productToCreate: CreateProduct = {

    name: "",
    descr: "",
    price: 0,
    category: {
      id: 0,
      name: "",
      description: ""
    },
    onSale: {
      id: 0
    },
    isFeatured: false,
    image: null
  }




  onFileSelected(event: any) {
    console.log(event);
    this.productToCreate.image = <File>event.target.files[0];

  }


  createProduct(productToCreate: CreateProduct) {
    // const fd = new FormData();
    // fd.append('image', this.productToCreate.image, this.productToCreate.image.name)
    // // console.log(this.productToCreate)
    // this.productToCreate.image = fd;
    // console.log(this.productToCreate)
    this.productService.create(productToCreate).subscribe({
    });
  }

  ngOnInit(): void {
  }

}
