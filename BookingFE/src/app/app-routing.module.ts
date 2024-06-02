import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RouteguardService } from './services/routeguard.service';
import { ResidenzeComponent } from './components/residenze/residenze.component';
import { CameraComponent } from './components/camera/camera.component';
import { SignupComponent } from './components/signup/signup.component';
import { ModificaUtenteComponent } from './components/modifica-utente/modifica-utente.component';
import { CamereDisponibiliComponent } from './components/camere-disponibili/camere-disponibili.component';
import { RicevutaComponent } from './components/ricevuta/ricevuta.component';
import { HomeComponent } from './components/home/home.component';
import { CameraSingolaComponent } from './components/camera-singola/camera-singola.component';
import { PrenotazioneComponent } from './components/prenotazione/prenotazione.component';
import { ResidenzaSingolaComponent } from './components/residenza-singola/residenza-singola.component';
import { AggiungiPrenotazioneComponent } from './components/aggiungi-prenotazione/aggiungi-prenotazione.component';
import { VisualizzaPrenotazioniComponent } from './components/visualizza-prenotazioni/visualizza-prenotazioni.component';
import { RicevutaResumeComponent } from './components/ricevuta-resume/ricevuta-resume.component';
import { RegistraStrutturaComponent } from './components/registra-struttura/registra-struttura.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "home", component: HomeComponent},
  {path: "residenze", component: ResidenzeComponent},
  {path: "residenza-singola", component: ResidenzaSingolaComponent},
  {path: "nuova-struttura", component: RegistraStrutturaComponent, canActivate: [RouteguardService]},
  {path: "camere", component: CameraComponent},
  {path: "camera-singola", component: CameraSingolaComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "logout", component: LogoutComponent, canActivate: [RouteguardService]},
  {path: "modifica", component: ModificaUtenteComponent, canActivate: [RouteguardService]},
  {path: "camereDisponibili", component: CamereDisponibiliComponent},
  {path: "prenotazione", component: PrenotazioneComponent, canActivate: [RouteguardService]},
  {path: "ricevuta", component: RicevutaComponent, canActivate: [RouteguardService]},
  {path: "aggiungi-prenotazione", component: AggiungiPrenotazioneComponent, canActivate: [RouteguardService]},
  {path: "visualizza-prenotazioni", component: VisualizzaPrenotazioniComponent, canActivate: [RouteguardService]},
  {path: "ricevuta-resume", component: RicevutaResumeComponent, canActivate: [RouteguardService]},
  {path: "**", component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
