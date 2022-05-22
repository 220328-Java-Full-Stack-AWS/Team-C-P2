import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {ReactiveFormsModule, FormsModule, FormBuilder} from '@angular/forms';
import {BaseLayoutComponent} from './shared/components/base-layout/base-layout.component';
import {HomeComponent} from './pages/home/home.component';
import {ProductsComponent} from './pages/products/products.component';
import {LoginComponent} from './shared/components/login/login.component';

import {ProductService} from './shared/services/product-service/product.service';

import {ProfileComponent} from './pages/profile/profile.component';
import {CartComponent} from './pages/cart/cart.component';
import {UpdatePasswordComponent} from './pages/update-password/update-password.component';
import {UpdateAddressComponent} from './pages/update-address/update-address.component';
import {UpdatePaymentComponent} from './pages/update-payment/update-payment.component';
import {UpdateProfileComponent} from './pages/update-profile/update-profile.component';
import { UpdateProductComponent } from './pages/admin/update-product/update-product.component';
import { CreateProductComponent } from './pages/admin/create-product/create-product.component';



@NgModule({
  declarations: [
    AppComponent,
    BaseLayoutComponent,
    HomeComponent,
    ProductsComponent,
    LoginComponent,
    ProfileComponent,
    CartComponent,
    UpdatePasswordComponent,
    UpdateAddressComponent,
    UpdatePaymentComponent,
    UpdateProfileComponent,
    UpdateProductComponent,
    CreateProductComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
