import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { BaseLayoutComponent } from './shared/components/base-layout/base-layout.component';
import { ProductsComponent } from './pages/products/products.component';
import { LoginComponent } from './shared/components/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CartComponent } from './pages/cart/cart.component';
import { UpdatePasswordComponent } from './pages/update-password/update-password.component';
import { UpdateAddressComponent } from './pages/update-address/update-address.component';
import { UpdatePaymentComponent } from './pages/update-payment/update-payment.component';
import { UpdateProfileComponent } from './pages/update-profile/update-profile.component';

const routes : Routes = [
  {
    path: '',
    component: BaseLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'products', component: ProductsComponent },
      { path: 'cart', component: CartComponent },
      { path: 'login', component: LoginComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'profile/updateProfile', component: UpdateProfileComponent },
      { path: 'profile/updatePassword', component: UpdatePasswordComponent },
      { path: 'profile/updateAddress', component: UpdateAddressComponent },
      { path: 'profile/updatePayment', component: UpdatePaymentComponent },
    ],
    canActivate: [ AuthGuard ] // Kicks us out to session/login possibly
  },
  {
    path: 'session',
    component: BaseLayoutComponent,
    children: [
      { path: '', component: LoginComponent },
      // { path: 'register', component: RegisterComponent }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
