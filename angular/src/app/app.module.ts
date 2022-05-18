import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { BaseLayoutComponent } from './shared/components/base-layout/base-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { ProductsComponent } from './pages/products/products.component';
import { LoginComponent } from './shared/components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    BaseLayoutComponent,
    HomeComponent,
    ProductsComponent,
    LoginComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
