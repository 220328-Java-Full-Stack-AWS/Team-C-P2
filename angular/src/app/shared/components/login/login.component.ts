import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserInfo } from '../../interfaces/User-Interface/User-info.interface';
import { AuthService } from '../../services/auth-service/auth.service';
import { UserService } from '../../services/user-service/user.service';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';

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

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }


  submitForm(): void {
    const formValue = this.loginForm.value;
    this.authService.login(formValue.username, formValue.password).subscribe({
      next: response => {
        // Success response
        console.log(`Hello ${response.data.firstName} ${response.data.lastName}`);
        
        // Sets current user info to behavior service and sets logged in to true
        this.baseLayout.isLoggedIn = true;
        this.user.userId = response.data.id;
        this.user.activeCartId = response.data.activeCartId;
        this.userService.setUserId(response.data.id);
        this.userService.setActiveCartId(response.data.activeCartId);
        localStorage.setItem('userInfo', JSON.stringify(this.user));
        
        this.router.navigate(['/']);
      },
      error: err => {
        // Notify the user username or password was incorrect
        console.error(err.error.message);
      }
    });
  }
}



