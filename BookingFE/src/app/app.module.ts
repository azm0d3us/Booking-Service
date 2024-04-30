import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { CarousalComponent } from './components/carousal/carousal.component';
import { ErrorComponent } from './components/error/error.component';
import { LogoutComponent } from './components/logout/logout.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { ResidenzeComponent } from './components/residenze/residenze.component';
import { HttpClientModule } from '@angular/common/http';
import { CameraComponent } from './components/camera/camera.component';
import { SignupComponent } from './components/signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    CarousalComponent,
    ErrorComponent,
    LogoutComponent,
    WelcomeComponent,
    ResidenzeComponent,
    CameraComponent,
    SignupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
