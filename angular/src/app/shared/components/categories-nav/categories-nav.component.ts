import { Component, OnInit } from '@angular/core';
import { Category } from '../../interfaces/Category-Interface/category.interface';
import { CategoriesService } from '../../services/category-service/category.service';

@Component({
  selector: 'app-categories-nav',
  templateUrl: './categories-nav.component.html',
  styleUrls: ['./categories-nav.component.scss']
})
export class CategoriesNavComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoriesService) { }

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe({
      next: response => {
        this.categories = (response as any).data
      },
      error: err => {
        console.error(err);
      }
    })
  }
}
