import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ChangePassword } from 'src/app/shared/interfaces/change-password.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { AuthService } from 'src/app/shared/services/auth-service/auth.service';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.scss']
})
export class UpdatePasswordComponent implements OnInit {

  paymentInfoForm : FormGroup = {} as FormGroup;
  error?: string = "Error.";

  private user : UserInfo = {
    userId : 0,
    activeCartId : 0
  }

  public password : ChangePassword = {
    userId : 0,
    currentPassword: " ",
    newPassword: " "
  }

  constructor(private fb : FormBuilder, private router : Router, private userService : UserService, public cookie : CookieService, private authService : AuthService) { }

  ngOnInit(): void {
  }

}
