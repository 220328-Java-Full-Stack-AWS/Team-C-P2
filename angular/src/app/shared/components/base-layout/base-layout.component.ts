import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileComponent } from 'src/app/pages/profile/profile.component';
import { Cart } from '../../interfaces/Cart-Interface/cart.interface';
import { category } from '../../interfaces/Product-Interface/category.interface';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';
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
  cart: Cart[] = [];

  constructor(
    private cookieService: CookieService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    private categoryService: CategoriesService
  ) { }

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

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

    this.userService.getCurrentActiveCartLength().subscribe((cartArray) => (
      this.cart = cartArray
    ));
    if (this.cookieService.getCookie('user_session')) {
      this.isLoggedIn = true;
    }
    else {
      this.isLoggedIn = false;
    }

    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));

    this.getCategories();
  }
}
