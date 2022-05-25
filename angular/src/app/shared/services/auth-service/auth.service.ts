import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserToRegister } from '../../interfaces/user-to-register.interface';
import {UserInfo} from "../../interfaces/User-Interface/user-info.interface";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authRoute : string = "http://Teamcp2ecommerce-env.eba-h8zawm3k.us-east-1.elasticbeanstalk.com/auth";
  private userInfo: UserInfo = {
    userId: 0,
    activeCartId: 0
  }

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.authRoute}/login`, {
      username: username,
      password: password
    }, { withCredentials: true });
  };

  checkUsername(username: string): Observable<any> {
    return this.http.post("http://Teamcp2ecommerce-env.eba-h8zawm3k.us-east-1.elasticbeanstalk.com/user/registrations", {
      username: username
    }, { withCredentials: true });
  }

  register(user: UserToRegister): Observable<any> {
    return this.http.post(`${this.authRoute}/register`, user, { withCredentials: true });
  };

  logout():Observable<any> {
    localStorage.setItem('userInfo', JSON.stringify(this.userInfo));
    return this.http.post(`${this.authRoute}/logout`, null, {observe: 'response', withCredentials: true});
  }
}
