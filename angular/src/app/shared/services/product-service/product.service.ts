import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { catchError, Observable, retry, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { Product } from "../../interfaces/Product-Interface/product.interface";
import { CreateProduct } from "../../interfaces/Product-Interface/product-create.interface";


@Injectable({
  providedIn: 'root'
})
export class ProductService {


  productsURL: string = "http://localhost:8080/products";

  constructor(private http: HttpClient) {
  }


  getAllProducts(): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.productsURL);
  }

  getProductById(id: number): Observable<Product> {
    console.log("in Service")
    return this.http
      .get<Product>(this.productsURL + "/" + id);
  }

  getProductPrice(id: number): Observable<Product> {
    return this.http
      .get<Product>(this.productsURL + "/" + id + "/price");
  }

  getAllFeatured(): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.productsURL + "/featured");
  }

  getByCategoryId(id: number): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.productsURL + "/category/" + id);
  }

  create(product: CreateProduct): Observable<CreateProduct> {

    return this.http.post<CreateProduct>(this.productsURL + "/create", product);
  }

  update(product: Product): Observable<Product> {

    return this.http.put<Product>(this.productsURL + "/update", product);
  }

  deleteById(id: number): Observable<any> {
    console.log("in Service")
    return this.http.delete<any>(this.productsURL + "/delete/" + id);
  }


}
