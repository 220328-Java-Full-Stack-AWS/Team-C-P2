import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserToRegister } from '../../interfaces/user-to-register.interface';
import { AuthService } from '../../services/auth-service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  credentialsForm : FormGroup = {} as FormGroup;
  userInfoForm : FormGroup = {} as FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    // Display errors
    this.credentialsForm = this.fb.group({
      "username": ["", Validators.compose([Validators.required, Validators.pattern("")])], // Same checks as login page
      "password": ["", Validators.compose([Validators.required, Validators.pattern("")])] // Same checks as login page
    });

    this.userInfoForm = this.fb.group({
      "firstName": ["", Validators.required], //Name pattern check
      "lastName": ["", Validators.required],
      "email": ["", Validators.required] // Email pattern check
    });
  }

  registerUser(): void {
    const user: UserToRegister = {
      username: this.credentialsForm.value.username,
      password: this.credentialsForm.value.password,
      firstName: this.userInfoForm.value.firstName,
      lastName: this.userInfoForm.value.lastName,
      email: this.userInfoForm.value.email
    }

    this.authService.register(user);
  }

}
