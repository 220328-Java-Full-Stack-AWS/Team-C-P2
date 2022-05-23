import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../../interfaces/category.interface';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    }
    categoriesURL: string = "http://localhost:8080/categories";

    constructor(private http: HttpClient) { }


    getAllCategories(): Observable<Category[]> {
        return this.http
        .get<Category[]>(this.categoriesURL);
    } 

    create(data: any): Observable<any> {
        return this.http.post(this.categoriesURL + "/create", data);
    }

    update(data: any): Observable<any> {
        return this.http.put<Category>(this.categoriesURL + "/update/", data);
    }

    deleteById(id: number): Observable<any> {
        return this.http.delete(this.categoriesURL + "/delete/" +id);
    }
}
