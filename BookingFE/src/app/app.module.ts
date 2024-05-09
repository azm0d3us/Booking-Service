import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';
import { LogoutComponent } from './components/logout/logout.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { ResidenzeComponent } from './components/residenze/residenze.component';
import { HttpClientModule } from '@angular/common/http';
import { CameraComponent } from './components/camera/camera.component';
import { SignupComponent } from './components/signup/signup.component';
import { CameraSingolaComponent } from './components/camera-singola/camera-singola.component';
import { FormsModule } from '@angular/forms';
import { DatepickerComponent } from './components/datepicker/datepicker.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModificaUtenteComponent } from './components/modifica-utente/modifica-utente.component';
import { PasswordResetComponent } from './components/password-reset/password-reset.component';
import { SuccessComponent } from './components/success/success.component';
import { CamereDisponibiliComponent } from './components/camere-disponibili/camere-disponibili.component';
import { RicevutaComponent } from './components/ricevuta/ricevuta.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    ErrorComponent,
    LogoutComponent,
    WelcomeComponent,
    ResidenzeComponent,
    CameraComponent,
    SignupComponent,
    CameraSingolaComponent,
    DatepickerComponent,
    ModificaUtenteComponent,
    PasswordResetComponent,
    SuccessComponent,
    CamereDisponibiliComponent,
    RicevutaComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
