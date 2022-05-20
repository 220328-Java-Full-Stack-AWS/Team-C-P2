import { HttpClient } from "@angular/common/http";


import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from "../../interfaces/Product-Interface/product.interface";


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productsURL: string = "http://localhost:8080/products";

  constructor(private http: HttpClient) {
  }

  getAllProducts(): Observable<any> {
    return this.http.get<Product>(this.productsURL);
  }

  getProductById(id: number): Observable<any> {
    console.log("in Service")
    return this.http.get<Product>(this.productsURL + "/" + id);
  }

  getProductPrice(id: number): Observable<any> {
    return this.http.get(this.productsURL + "/" + id + "/price");
  }

}
