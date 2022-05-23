import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { MatTooltip } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { UserInfo } from '../../shared/interfaces/User-Interface/user-info.interface';
import { UserToRegister } from '../../shared/interfaces/user-to-register.interface';
import { AuthService } from '../../shared/services/auth-service/auth.service';
import { UserService } from '../../shared/services/user-service/user.service';
import { BaseLayoutComponent } from '../../shared/components/base-layout/base-layout.component';
import { UserAddress } from '../../shared/interfaces/user-address.interface';
import { CookieService } from '../../shared/services/cookie-service/cookie.service';

@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.scss']
})
export class UpdateAddressComponent implements OnInit {

  @ViewChild("stepper") stepper! : MatStepper;
  addressInfoForm : FormGroup = {} as FormGroup;
  error?: string = "Error.";
  
  public address : UserAddress = {
    id : 0,
    addressLine1: " ",
    addressLine2: " ",
    city: " ",
    postalCode: 0,
    country: " ",
    phoneNumber: " "
  }

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }


  constructor(private fb : FormBuilder, private router : Router, private userService : UserService, public cookie : CookieService) { }

  ngOnInit(): void {
    // store the user cookie and info
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));

    // build the address info form
    this.addressInfoForm = this.fb.group({
      // check for alphanumeric string that has at least one number
      "addressLine1" : [""],
      "addressLine2" : [""],
      // check for A-z only
      "city" : [""],
      // check for numeric only
      "postalCode" : [""],
      // A-z only
      "country" : [""],
      // numeric only, 10 digits long, maybe phone number (xxx-xxx-xxxx) layout?
      "phoneNumber" : [""]      
    });

  }

  confirmAddress() : void {
    // create new address to update user
    const newAddress : UserAddress = {
      id : this.user.userId,
      addressLine1: this.addressInfoForm.value.addressLine1,
      addressLine2: this.addressInfoForm.value.addressLine2,
      city: this.addressInfoForm.value.city,
      postalCode: this.addressInfoForm.value.postalCode,
      country: this.addressInfoForm.value.country,
      phoneNumber: this.addressInfoForm.value.phoneNumber
    };
    this.address = newAddress;
    // go to confirm address step
    this.stepper.next();
  }

  updateAddress(): void {
    // subscribe to update payment method and route to user profile
    console.log(this.address);
    this.userService.updateUserAddress(this.address).subscribe((json : any) => {
      console.log(json);
      this.router.navigate(["/profile"])
    });

  }

  // function to compare input and display tooltip
  /*checkInput(control: AbstractControl | null, tooltip: MatTooltip) {
    if (!control!.valid) {
      tooltip.show();
    }
    else {
      tooltip.hide();
    }
  }*/

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

}
