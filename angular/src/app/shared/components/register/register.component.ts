import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { MatTooltip } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { UserInfo } from '../../interfaces/User-Interface/User-info.interface';
import { UserToRegister } from '../../interfaces/user-to-register.interface';
import { AuthService } from '../../services/auth-service/auth.service';
import { UserService } from '../../services/user-service/user.service';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  @ViewChild("stepper") stepper! : MatStepper;
  credentialsForm : FormGroup = {} as FormGroup;
  userInfoForm : FormGroup = {} as FormGroup;
  error?: string = "This is an error";

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router, private baseLayout: BaseLayoutComponent, private userService: UserService) { }

  ngOnInit(): void {
    this.credentialsForm = this.fb.group({
      // Matches alphanumeric string that may include '$' & 8-20 characters long
      "username": ["", Validators.compose([Validators.required, Validators.pattern(/^[0-9a-z$]{8,20}$/i)])],
      // Password must: Have 8-20 characters, Include at least one capital letter, Include at least one special character.
      "password": ["", Validators.compose([Validators.required, Validators.pattern(/^(?=.*[A-Z]+)(?=.*[!\"#$%&'()*+,\-.\/:;<=>?@[\]^_`{|}~]).{8,20}$/)])],
      "passwordCheck":["", Validators.compose([Validators.required, Validators.pattern(/^(?=.*[A-Z]+)(?=.*[!\"#$%&'()*+,\-.\/:;<=>?@[\]^_`{|}~]).{8,20}$/)])]
    });

    this.userInfoForm = this.fb.group({
      // First & Last name must: Contain letters, No spaces numbers or special chars, Between 1-60 characters
      "firstName": ["", Validators.compose([Validators.required, Validators.pattern(/^[a-z]{1,60}$/i)])],
      "lastName": ["", Validators.compose([Validators.required, Validators.pattern(/^[a-z]{1,60}$/i)])],
      // Supports double domain, Cannot start with special char, required '@' char
      "email": ["", Validators.compose([Validators.required, Validators.pattern(/^((?![\.!@#$%^&*()-_=+])[\w/-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$/)])]
    });
  }

  /**
   * Checks to see if username specified already exists before continuing
   */
  checkUsername(): void {
    if (this.credentialsForm.value.password != this.credentialsForm.value.passwordCheck){
      this.showError("The passwords do not match");
      return;
    }

    this.authService.checkUsername(this.credentialsForm.value.username).subscribe({
      next: response => {
        this.closeError();
        this.stepper.next();
      },
      error: err => {
        this.showError("The specified username already exists");
      }
    });
  }

  registerUser(): void {
    const user: UserToRegister = {
      username: this.credentialsForm.value.username,
      password: this.credentialsForm.value.password,
      firstName: this.userInfoForm.value.firstName,
      lastName: this.userInfoForm.value.lastName,
      email: this.userInfoForm.value.email
    };

    this.authService.register(user).subscribe({
      next: response => {
        console.log(`Hello ${response.data.firstName} ${response.data.lastName}`);
        this.baseLayout.isLoggedIn = true;
        this.user.userId = response.data.id;
        this.user.activeCartId = response.data.activeCartId;
        this.userService.setUserId(response.data.id);
        this.userService.setActiveCartId(response.data.activeCartId);
        localStorage.setItem('userInfo', JSON.stringify(this.user));
        this.router.navigate(["/"]);
      },
      error: err => {
        console.log(err);
        this.showError("")
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
