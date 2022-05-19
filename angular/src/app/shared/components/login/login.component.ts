import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

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
    private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      "username": ["", Validators.compose([Validators.required, Validators.pattern("")])], // Add character count check?
      "password": ["", Validators.compose([Validators.required, Validators.pattern("")])] // Add minimum requirement check?
    });
  }

  submitForm(): void {
    const formValue = this.loginForm.value;
    this.authService.login(formValue.username, formValue.password).subscribe({
      next: response => {
        // Success response
        console.log(`Hello ${response.data.firstName} ${response.data.lastName}`);
        this.router.navigate(['/']);
      },
      error: err => {
        // Notify the user username or password was incorrect
        console.error(err.error.message);
      }
    });
  }
}
