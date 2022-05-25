import { Location } from "@angular/common";
import { Component, OnInit, ViewChild } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { MatStepper } from "@angular/material/stepper";
import { Router } from "@angular/router";
import { UserInfo } from "src/app/shared/interfaces/User-Interface/user-info.interface";
import { UserPayment } from "src/app/shared/interfaces/user-payment.interface";
import { CookieService } from "src/app/shared/services/cookie-service/cookie.service";
import { UserService } from "src/app/shared/services/user-service/user.service";


@Component({
  selector: 'app-update-payment',
  templateUrl: './update-payment.component.html',
  styleUrls: ['./update-payment.component.scss']
})
export class UpdatePaymentComponent implements OnInit {

  @ViewChild("stepper") stepper! : MatStepper;
  paymentInfoForm : FormGroup = {} as FormGroup;
  error?: string = "Error.";

  private user : UserInfo = {
    userId : 0,
    activeCartId : 0
  }

  public newPayment : UserPayment = {
    userId : 0,
    network: " ",
    issuer: " ",
    cardNumber: "",
    securityCode: 0,
    expDate: " "
  }

  constructor(private fb : FormBuilder, private router : Router, private location : Location, private userService : UserService, public cookie : CookieService) { }

  ngOnInit(): void {

    // store the user cookie and info
    this.cookie.getCookie('user_session');
    this.userService.getCurrentUser().subscribe((user) => (
      this.user = user
    ));

    // build payment info form
    this.paymentInfoForm = this.fb.group({
    // TODO: implement validation
      // check for A-z only
      "network" : [""],
      // check for A-z only
      "issuer" : [""],
      // check for numeric only, exactly 12 digits
      "cardNumber" : [""],
      // check for numeric only, exactly 3 digits
      "securityCode" : [""],
      // numeric only, yyyy-mm-dd format?
      "expDate" : [""]
    });

  }
  // function to input the new payment information and move to the next step
  inputPayment() : void {
  // create new payment to update user
    const payment : UserPayment = {
      userId : this.user.userId,
      network: this.paymentInfoForm.value.network,
      issuer: this.paymentInfoForm.value.issuer,
      cardNumber: this.paymentInfoForm.value.cardNumber,
      securityCode: this.paymentInfoForm.value.securityCode,
      expDate: this.paymentInfoForm.value.expDate
    };
    this.newPayment = payment;
    // go to confirm payment step
    this.stepper.next();
  }

  // function to confirm new payment information and update user payment
  confirmPayment(): void {
    // subscribe to update payment method and route to user profile
    console.log(this.newPayment);
    this.cookie.getCookie('user_session');
    this.userService.updateUserPayment(this.newPayment).subscribe((json : UserPayment) => {
      console.log(json);
      this.location.back();
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
