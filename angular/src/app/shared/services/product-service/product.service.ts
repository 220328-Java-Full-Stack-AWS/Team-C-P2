import { HttpClient } from "@angular/common/http";


import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from "../../interfaces/Product-Interface/product.interface";


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productsURL: string = "http://localhost:8080/products";

  constructor(private http: HttpClient) {
  }

  // getAllProducts(): Observable<Product[]> {
  //   return this.http.get<Product[]>(this.productsURL);
  // }
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsURL);
  }

  getProductById(id: number): Observable<Product> {
    console.log("in Service")
    return this.http.get<Product>(this.productsURL + "/" + id);
  }

  getProductPrice(id: number): Observable<Product> {
    return this.http
      .get<Product>(this.productsURL + "/" + id + "/price").pipe(
        map(res => res));
  }

  getAllFeatured(): Observable<Product> {
    return this.http.get<Product>(this.productsURL + "/featured");
  }

}
