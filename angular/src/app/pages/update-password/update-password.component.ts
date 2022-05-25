import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTooltip } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { PasswordToChange } from 'src/app/shared/interfaces/password-to-change.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { AuthService } from 'src/app/shared/services/auth-service/auth.service';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';
import { BaseLayoutComponent } from 'src/app/shared/components/base-layout/base-layout.component';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.scss']
})
export class UpdatePasswordComponent implements OnInit {

  credentialsForm : FormGroup = {} as FormGroup;
  error?: string = "Error.";

  private user : UserInfo = {
    userId : 0,
    activeCartId : 0
  }

  public password : PasswordToChange = {
    userId : 0,
    currentPassword: " ",
    newPassword: " "
  }

  constructor(
    private fb : FormBuilder, 
    private router : Router, 
    private userService : UserService, 
    public cookie : CookieService, 
    private authService : AuthService,
    private baseLayout : BaseLayoutComponent) { }

  ngOnInit(): void {

    // store the user cookie and info
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));

    // build new password form
    this.credentialsForm = this.fb.group({
      // current password
      "currentPassword": [""],
      // Password must: Have 8-20 characters, Include at least one capital letter, Include at least one special character.
      "newPassword": ["", Validators.compose([Validators.required, Validators.pattern(/^(?=.*[A-Z]+)(?=.*[!\"#$%&'()*+,\-.\/:;<=>?@[\]^_`{|}~]).{8,20}$/)])],
      "newPasswordCheck":["", Validators.compose([Validators.required, Validators.pattern(/^(?=.*[A-Z]+)(?=.*[!\"#$%&'()*+,\-.\/:;<=>?@[\]^_`{|}~]).{8,20}$/)])]
    });


  }

  // function to attempt to change user password - log out on success, error message if failed
  changePassword(): void {
    const toChange : PasswordToChange = {
      userId : this.user.userId,
      currentPassword : this.credentialsForm.value.currentPassword,
      newPassword : this.credentialsForm.value.newPassword
    }
    // if the re-enter password does not match, show error
    if (this.credentialsForm.value.newPassword != this.credentialsForm.value.newPasswordCheck) {
      this.showError("The passwords do not match");
    }
    else {
      this.userService.updateUserPassword(toChange).subscribe(
        (res) => {
          if (res.body.statusCode == 200) {
            // if success logout
            this.successLogOut();
          }
          else {
            this.showError("Current Password is Incorrect.");
          console.error(res);
          }
        },
        (err) => {
          this.showError("Current Password is Incorrect.");
        }
      );
    }
    
  }

  // function to log out user if password change was successful
  successLogOut(): void {

    this.authService.logout().subscribe(
      (res) => {
        if (res.status == 200) {
          this.baseLayout.isLoggedIn = false;
          this.cookie.deleteCookie('user_session');
          this.router.navigate(['/']);
        }
      }, (err) => {
        console.error(err.error.message);
        }
    );


  }

  // function to close error window
  closeError(): void {
    const el = document.querySelector(".error") as HTMLElement;

    if(!el.classList.contains("hide")){
      el.classList.add("hide");
      this.error = "";
    }
  }

  // function to show errors
  showError(errorMessage: string): void {
    const el = document.querySelector('.error') as HTMLElement;

    this.closeError();
    if(el.classList.contains("hide")) {
      this.error = errorMessage;
      el.classList.remove("hide");
    }
  }

  checkInput(control: AbstractControl | null, tooltip: MatTooltip) {
    this.cookie.getCookie('user_session');
    if (!control!.valid) {
      tooltip.show();
    }
    else {
      tooltip.hide();
    }
  }

}
