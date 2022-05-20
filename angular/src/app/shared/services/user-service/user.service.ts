import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserProfile } from '../../interfaces/User-Interface/user-profile.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userURL: string = "http://localhost:8080/user";

  constructor(private http: HttpClient) {
  }

  getUser(id: number): Observable<any> {

    return this.http.get<UserProfile>(this.userURL + "/" + id + "/profile", {withCredentials:true});
  }
}
