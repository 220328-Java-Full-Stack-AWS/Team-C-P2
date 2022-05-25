import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { category } from '../../interfaces/Product-Interface/category.interface';
import { AuthService } from '../../services/auth-service/auth.service';
import { CategoriesService } from '../../services/category-service/category.service';
import { CookieService } from '../../services/cookie-service/cookie.service';
import { UserService } from '../../services/user-service/user.service';

@Component({
  selector: 'app-base-layout',
  templateUrl: './base-layout.component.html',
  styleUrls: ['./base-layout.component.scss']
})
export class BaseLayoutComponent implements OnInit {
  isLoggedIn: boolean = false;
  itemCount: number = 0;

  constructor(
    private cookieService: CookieService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    private categoryService: CategoriesService
  ) { }

  logout() {
    this.authService.logout().subscribe(
      (res) => {
        if (res.status == 200) {
          this.cookieService.deleteCookie('user_session');
          this.isLoggedIn = false;
          this.router.navigate(['/']);
        }
      }, (err) => {
        console.error(err.error.message);
      }
    );
  }

  userSubscription: any;
  categories: Array<category> = [];

  getCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: response => {
        console.log(response)
        this.categories = (response as any).data;
      },
      error: err => {
        console.error(err);
      }
    });
  }

  ngOnInit(): void {
    if(this.cookieService.getCookie('user_session')) {
      this.isLoggedIn = true;
    }
    else {
      this.isLoggedIn = false;
    }

    // Get Cart Item count
    this.userService.getCurrentCartSubject().subscribe((currentCart: any) => {
      let count = 0;
      // Loop through cart items add to count
      Array.prototype.forEach.call(currentCart, (cartItem: any) => {
        count += cartItem.cartItem ? Number(cartItem.cartItem?.quantity!) : Number(cartItem.quantity);
      });
      // Pass count to view
      this.itemCount = count;
    });
    this.getCategories();
  }
}
