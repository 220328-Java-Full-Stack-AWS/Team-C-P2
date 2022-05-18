import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { BaseLayoutComponent } from './shared/base-layout/base-layout.component';
import { SessionLayoutComponent } from './shared/session-layout/session-layout.component';
import { ProductsComponent } from './pages/products/products.component';

const routes : Routes = [
  {
    path: '',
    component: BaseLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'products', component: ProductsComponent },
      // ...
    ],
    // canActivate: [ AuthGuard ] // Kicks us out to /session/login possibly
  },
  {
    path: 'session',
    component: SessionLayoutComponent,
    // children: [
    //   {
    //     path: 'login',
    //     component: LoginComponent
    //   },
    //   {
    //     path: 'register',
    //     component: RegisterComponent
    //   }
    // ]
  }
]

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
