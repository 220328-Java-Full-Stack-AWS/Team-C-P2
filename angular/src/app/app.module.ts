import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ReactiveFormsModule, FormsModule, FormBuilder } from '@angular/forms';
import { BaseLayoutComponent } from './shared/components/base-layout/base-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { ProductsComponent } from './pages/products/products.component';
import { LoginComponent } from './shared/components/login/login.component';

import { ProductService } from './shared/services/product-service/product.service';

import { ProfileComponent } from './pages/profile/profile.component';
import { CartComponent } from './pages/cart/cart.component';
import { UpdatePasswordComponent } from './pages/update-password/update-password.component';
import { UpdateAddressComponent } from './pages/update-address/update-address.component';
import { UpdatePaymentComponent } from './pages/update-payment/update-payment.component';
import { UpdateProfileComponent } from './pages/update-profile/update-profile.component';
import { UpdateProductComponent } from './pages/admin/update-product/update-product.component';
import { CreateProductComponent } from './pages/admin/create-product/create-product.component';


import { RegisterComponent } from './shared/components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { FeaturedProductsComponent } from './shared/components/featured-products/featured-products.component';
import { CategoriesNavComponent } from './shared/components/categories-nav/categories-nav.component';

import { ProductViewComponent } from './product-view/product-view.component';


@NgModule({
  declarations: [
    AppComponent,
    BaseLayoutComponent,
    HomeComponent,
    ProductsComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    CartComponent,
    CreateProductComponent,
    UpdatePasswordComponent,
    UpdateAddressComponent,
    UpdatePaymentComponent,
    UpdateProfileComponent,
    UpdateProductComponent,
    RegisterComponent,
    UpdateProfileComponent,
    FeaturedProductsComponent,
    CategoriesNavComponent,
    ProductViewComponent,


  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatStepperModule,
    FlexLayoutModule,
    MatTooltipModule,
    MatIconModule,
    MatButtonModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
