import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../../interfaces/category.interface';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  categoriesURL: string = "http://localhost:8080/categories";

  constructor(private http: HttpClient) { }


  getAllCategories(): Observable<Category[]> {
    return this.http
      .get<Category[]>(this.categoriesURL);
  } 


}
