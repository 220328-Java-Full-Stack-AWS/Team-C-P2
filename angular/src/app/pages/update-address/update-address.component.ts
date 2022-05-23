import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { MatTooltip } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { UserInfo } from '../../interfaces/User-Interface/user-info.interface';
import { UserToRegister } from '../../interfaces/user-to-register.interface';
import { AuthService } from '../../services/auth-service/auth.service';
import { UserService } from '../../services/user-service/user.service';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';

@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.scss']
})
export class UpdateAddressComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
