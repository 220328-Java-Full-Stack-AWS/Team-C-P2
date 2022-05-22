import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTooltip } from '@angular/material/tooltip';
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

  error?: string;
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
      // Matches alphanumeric string that may include '$' & 8-20 characters long
      "username": ["", Validators.compose([Validators.required, Validators.pattern(/^[0-9a-z$]{8,20}$/i)])],
      // Password must: Have 8-20 characters, Include at least one capital letter, Include at least one special character.
      "password": ["", Validators.compose([Validators.required, Validators.pattern(/^(?=.*[A-Z]+)(?=.*[!\"#$%&'()*+,\-.\/:;<=>?@[\]^_`{|}~]).{8,20}$/)])]
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
        //TODO: Display error
        console.error(err.error.message);
      }
    });
  }

  checkInput(control: AbstractControl | null, tooltip: MatTooltip) {
    if (!control!.valid) {
      tooltip.show();
    }
    else {
      tooltip.hide();
    }
  }

  closeError(): void {
    const el = document.querySelector(".error") as HTMLElement;

    if(!el.classList.contains("hide")){
      el.classList.add("hide");
      this.error = "";
    }
  }

  showError(errorMessage: string): void {
    const el = document.querySelector('.error') as HTMLElement;

    this.closeError();
    if(el.classList.contains("hide")) {
      this.error = errorMessage;
      el.classList.remove("hide");
    }
  }
}



