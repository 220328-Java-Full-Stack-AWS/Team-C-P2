import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileComponent } from 'src/app/pages/profile/profile.component';
import { Cart } from '../../interfaces/Cart-Interface/cart.interface';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';
import { AuthService } from '../../services/auth-service/auth.service';
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
    private cookieService:CookieService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    ){}

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

  ngOnInit(): void {
    if(this.cookieService.getCookie('user_session')) {
      this.isLoggedIn = true;
    }
    else {
      this.isLoggedIn = false;
    }
    // Get Cart Item count
    this.userService.getCurrentCartSubject().subscribe((currentCart: any) => {
      console.log(currentCart);
      let count = 0;
      // Loop through cart items add to count
      Array.prototype.forEach.call(currentCart, (cartItem: Cart) => {
        count += cartItem.cartItem?.quantity!;
      });
      // Pass count to view
      this.itemCount = count;
    });
  }
}
