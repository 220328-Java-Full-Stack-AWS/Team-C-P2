import { Component } from '@angular/core';
import {IProducts} from "./pages/onsale/IProduct.interface";
import {OnSaleComponent} from "./pages/onsale/onsale.component";
import {OnSaleService} from "./pages/onsale/onsale.service";

@Component({
  selector: 'app-root',
  // templateUrl: './app.component.html',
  template: `
    <nav class='navbar navbar-expand navbar-light bg-light'>
      <a class='navbar-brand'>{{onSaleProducts}}</a>
      <ul class='nav nav-pills'>
        <li><a class='nav-link' routerLinkActive='active' routerLink='/home'>Home</a></li>
        <li><a class='nav-link' routerLinkActive='active' routerLink='/login'>Login</a></li>
        <li><a class='nav-link' routerLinkActive='active' routerLink='/products'>Products</a></li>
        <li><a class='nav-link' routerLinkActive='active' routerLink='/onsale'>On Sale</a></li>

      </ul>
    </nav>
    <div class='container'>
      <router-outlet></router-outlet>
    </div>
  `,
   styleUrls: ['./app.component.scss']

})
export class AppComponent {
  // title = 'front';
  onSaleProducts: string = 'AME Store!';
  title: string="";


}
