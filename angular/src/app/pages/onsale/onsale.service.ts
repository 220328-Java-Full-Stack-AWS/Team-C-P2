import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError,tap} from "rxjs/operators";
import {IProducts} from "./IProduct.interface";





@Injectable({
  providedIn:'root' //shared globally
})
export class OnSaleService {

  private onSaleProductUrl =  'api/products/onsaleproduct.json';//webserver address
  constructor(private http: HttpClient) {
  }

  getOnSaleProducts(): Observable<IProducts[]> {
    return this.http.get<IProducts[]>(this.onSaleProductUrl).pipe(
      tap(data => console.log('All', JSON.stringify(data))), catchError(this.handleError)
    );
//   [
//     {
//   "id": 1,
//   "image": "assets/images/yankees cap.jpg",
//   "name": "Leaf Rake",
//   "is_featured": "March 19, 2021",
//   "descr": "Nice Cap for Yankees Fans.",
//   "price": 19.95,
//   "on_sale": 16.95
//
// },
// {
//   "id": 2,
//   "image": "assets/images/football.jpg",
//   "name": "FootBall",
//   "is_featured": "Jan 19, 2022",
//   "descr": "For the Football Fan",
//   "price": 30.95,
//   "on_sale": 20.95
// },
// {
//   "id": 3,
//   "image": "assets/images/bat.jpg",
//   "name": "Slugger",
//   "is_featured": "March 25, 2021",
//   "descr": "Hit a Home Run",
//   "price": 19.95,
//   "on_sale": 26.95
// },
// {
//   "id": 1,
//   "image": "assets/images/basket ball",
//   "name": "Shoot some Hoops",
//   "is_featured": "November 19, 2021",
//   "descr": "Lets shoot some hoops.",
//   "price": 37.95,
//   "on_sale": 19.95
// }
// ]
  }


  private handleError(err: HttpErrorResponse): Observable<never> {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage); //Throws an Error to the calling code
  }
}
