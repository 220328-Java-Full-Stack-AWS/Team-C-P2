import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileComponent } from 'src/app/pages/profile/profile.component';
import { UserInfo } from '../../interfaces/User-Interface/User-info.interface';
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

  constructor(
    private cookieService:CookieService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    
    ){ 
  };

  userId = 0;

  



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

  ngOnInit() {
    if(this.cookieService.getCookie('user_session')) {
      this.isLoggedIn = true;
    }
    else {
      this.isLoggedIn = false;
    }

    this.userSubscription = this.userService.getInstance().subscribe({
      next: 
      (data: UserInfo) => {
        this.userId = data.userId;
      }
      
    });

    console.log(this.userId);
  }
}
