import { Component, OnInit } from '@angular/core';
import { UserProfile } from 'src/app/shared/interfaces/User-Interface/user-profile.interface';
import { CookieService } from 'src/app/shared/services/cookie-service/cookie.service';
import { UserService } from 'src/app/shared/services/user-service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  id: number = 0;

  userQuery: UserProfile = {
    id: 0,
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    role: "",
    activeCartId: 0,
    userAddress: {
      id: 0,
      addressLine1: "",
      addressLine2: "string",
      city: "",
      postalCode: 0,
      country: "",
      phoneNumber: ""
    },
    payment: {
      id: 0,
      network: "",
      issuer: "",
      cardNumber: 0,
      securityCode: 0,
      expirationDate: ""
    },
    dateCreated: "",
    dateModifies: ""
  }

  getUserById(id:number) {
    this.UserService.getUser(id).subscribe((json: any) => {
      console.log(json);
      this.userQuery = {
        id: json.data.id,
        username: json.data?.username,
        firstName: json.data?.firstName,
        lastName: json.data?.lastName,
        email: json.data?.email,
        role: json.data?.role,
        activeCartId: json.data?.activeCartId,
        userAddress: {
          id: json.data.userAddress?.id,
          addressLine1: json.data.userAddress?.addressLine1,
          addressLine2: json.data.userAddress?.addressLine2,
          city: json.data.userAddress?.city,
          postalCode: json.data.userAddress?.postalCode,
          country: json.data.userAddress?.country,
          phoneNumber: json.data.userAddress?.phoneNumber
        },
        payment: {
          id: json.data.payment?.id,
          network: json.data.payment?.network,
          issuer: json.data.payment?.issuer,
          cardNumber: json.data.payment?.cardNumber,
          securityCode: json.data.payment?.securityCode,
          expirationDate: json.data.payment?.expirationDate
        },
        dateCreated: json.data?.dateCreated,
        dateModifies: json.data?.dateModifies
      }
    })
  }
  constructor(private UserService: UserService, public cookie:CookieService) { }

  ngOnInit() {
    this.cookie.getCookie('user_session');
    console.log(this.cookie);
  }

}
