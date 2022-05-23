import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { Router } from '@angular/router';

import { UserAddress } from 'src/app/shared/interfaces/user-address.interface';
import { UserInfo } from 'src/app/shared/interfaces/User-Interface/user-info.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.scss']
})
export class UpdateAddressComponent implements OnInit {

  @ViewChild("stepper") stepper! : MatStepper;
  addressInfoForm : FormGroup = {} as FormGroup;
  error?: string = "Error.";

  private user: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  public newAddress : UserAddress = {
    id : 0,
    addressLine1: " ",
    addressLine2: " ",
    city: " ",
    postalCode: 0,
    country: " ",
    phoneNumber: " "
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
      // TODO: implement validation
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

  // function to input the new address information and move to the next step
  inputAddress() : void {
    // create new address to update user
    const address : UserAddress = {
      id : this.user.userId,
      addressLine1: this.addressInfoForm.value.addressLine1,
      addressLine2: this.addressInfoForm.value.addressLine2,
      city: this.addressInfoForm.value.city,
      postalCode: this.addressInfoForm.value.postalCode,
      country: this.addressInfoForm.value.country,
      phoneNumber: this.addressInfoForm.value.phoneNumber
    };
    this.newAddress = address;
    // go to confirm address step
    this.stepper.next();
  }

  // function to confirm new address information and update address
  confirmAddress(): void {
    // subscribe to update payment method and route to user profile
    this.newAddress.id = this.user.userId;
    console.log(this.newAddress);
    this.cookie.getCookie('user_session');
    this.userService.updateUserAddress(this.newAddress).subscribe((json) => {
      console.log(json);
      this.router.navigate(["/profile"])
    });

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

  // function to compare input and validate on UI
  // TODO: implement with validation
  /*checkInput(control: AbstractControl | null, tooltip: MatTooltip) {
    if (!control!.valid) {
      tooltip.show();
    }
    else {
      tooltip.hide();
    }
  }*/
}

