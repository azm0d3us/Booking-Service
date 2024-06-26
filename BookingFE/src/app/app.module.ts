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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatepickerComponent } from './components/datepicker/datepicker.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModificaUtenteComponent } from './components/modifica-utente/modifica-utente.component';
import { PasswordResetComponent } from './components/password-reset/password-reset.component';
import { CamereDisponibiliComponent } from './components/camere-disponibili/camere-disponibili.component';
import { RicevutaComponent } from './components/ricevuta/ricevuta.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HomeComponent } from './components/home/home.component';
import { PrenotazioneComponent } from './components/prenotazione/prenotazione.component';
import { ResidenzaSingolaComponent } from './components/residenza-singola/residenza-singola.component';
import { CustomCurrencyPipe } from './pipes/custom-currency.pipe';
import { AggiungiPrenotazioneComponent } from './components/aggiungi-prenotazione/aggiungi-prenotazione.component';
import { NgxSelectModule } from 'ngx-select-ex';
import { VisualizzaPrenotazioniComponent } from './components/visualizza-prenotazioni/visualizza-prenotazioni.component';
import { TimestampToDatePipe } from './pipes/timestamp-to-date.pipe';
import { RicevutaResumeComponent } from './components/ricevuta-resume/ricevuta-resume.component';
import { RegistraStrutturaComponent } from './components/registra-struttura/registra-struttura.component';

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
    CamereDisponibiliComponent,
    RicevutaComponent,
    HomeComponent,
    PrenotazioneComponent,
    ResidenzaSingolaComponent,
    CustomCurrencyPipe,
    AggiungiPrenotazioneComponent,
    VisualizzaPrenotazioniComponent,
    TimestampToDatePipe,
    RicevutaResumeComponent,
    RegistraStrutturaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule,
    NgxSelectModule,
    ReactiveFormsModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
