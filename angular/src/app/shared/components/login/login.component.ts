import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HomeComponent } from 'src/app/pages/home/home.component';
import { AuthService } from '../../services/auth-service/auth.service';
import { UserService } from '../../services/user-service/user.service';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { UserInfo } from '../../interfaces/User-Interface/User-info.interface';
import { Observable, of } from 'rxjs';
import { ProfileComponent } from 'src/app/pages/profile/profile.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup = {} as FormGroup;

  constructor(
    private fb : FormBuilder,
    private authService: AuthService,
    private router: Router,
    private baseLayout: BaseLayoutComponent,
    private userService: UserService
    ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      "username": ["", Validators.compose([Validators.required, Validators.pattern("")])], // Add character count check?
      "password": ["", Validators.compose([Validators.required, Validators.pattern("")])] // Add minimum requirement check?
    });
  }
  userInfo: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  sendMessage(info: UserInfo) {
    this.userService.getInstance().next({userId: info.userId, activeCartId: info.activeCartId});

  }


  submitForm(): void {
    const formValue = this.loginForm.value;
    this.authService.login(formValue.username, formValue.password).subscribe({
      next: response => {
        // Success response
        console.log(`Hello ${response.data.firstName} ${response.data.lastName}`);
        this.baseLayout.isLoggedIn = true;
        
        this.userInfo = {
          userId: response.data.id,
          activeCartId: response.data.activeCartId
        }
        console.log("login userID:" + this.userInfo.userId);

        
        this.router.navigate(['/']);
      },
      error: err => {
        // Notify the user username or password was incorrect
        console.error(err.error.message);
      }
    });
  }
}



