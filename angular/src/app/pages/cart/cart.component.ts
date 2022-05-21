import { Component, OnInit } from '@angular/core';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/User-info.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  constructor(
    private userService: UserService,
    private cookie: CookieService,
    
  ) { }

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  

  ngOnInit(): void {
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));
  }

}
