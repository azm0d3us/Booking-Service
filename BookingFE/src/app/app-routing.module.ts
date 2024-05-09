import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RouteguardService } from './services/routeguard.service';
import { ResidenzeComponent } from './components/residenze/residenze.component';
import { CameraComponent } from './components/camera/camera.component';
import { SignupComponent } from './components/signup/signup.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { ModificaUtenteComponent } from './components/modifica-utente/modifica-utente.component';
import { SuccessComponent } from './components/success/success.component';
import { CamereDisponibiliComponent } from './components/camere-disponibili/camere-disponibili.component';
import { RicevutaComponent } from './components/ricevuta/ricevuta.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "carousel", component: CarouselComponent},
  {path: "residenze", component: ResidenzeComponent},
  {path: "camere", component: CameraComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "logout", component: LogoutComponent, canActivate: [RouteguardService]},
  {path: "modifica", component: ModificaUtenteComponent, canActivate: [RouteguardService]},
  {path: "camereDisponibili", component: CamereDisponibiliComponent},
  {path: "ricevuta", component: RicevutaComponent, canActivate: [RouteguardService]},
  {path: "success", component: SuccessComponent, canActivate: [RouteguardService]},
  {path: "home", component: HomeComponent}, //<---- must put this as entry point.
  {path: "**", component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
