import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../../interfaces/Category-Interface/category.interface';

@Component({
  selector: 'app-categories-nav',
  templateUrl: './categories-nav.component.html',
  styleUrls: ['./categories-nav.component.scss']
})
export class CategoriesNavComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe({
      next: response => {
        this.categories = (response as any).data;
      },
      error: err => {
        // Todo: Handle error
      }
    })
  }
}

@Injectable({
  providedIn: 'root'
})
class CategoryService {
  constructor(private http: HttpClient){}

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>("http://localhost:8080/categories");
  }
}
