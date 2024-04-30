import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RouteguardService } from './services/routeguard.service';
// import { WelcomeComponent } from './components/welcome/welcome.component';
import { CarousalComponent } from './components/carousal/carousal.component';
import { ResidenzeComponent } from './components/residenze/residenze.component';
import { CameraComponent } from './components/camera/camera.component';
import { SignupComponent } from './components/signup/signup.component';

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "carousel", component: CarousalComponent},
  {path: "residenze", component: ResidenzeComponent},
  {path: "camere", component: CameraComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "logout", component: LogoutComponent},
  {path: "**", component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
