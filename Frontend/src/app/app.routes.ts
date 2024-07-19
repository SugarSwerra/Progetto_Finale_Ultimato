import { Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {CorsiComponent} from "./corsi/corsi.component";
import {ProfileComponent} from "./profile/profile.component";
import {ProfilePageComponent} from "./profile-page/profile-page.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {UnauthorizedComponent} from "./unauthorized/unauthorized.component";

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'courses', component: CorsiComponent},
  {path: 'profile', component: ProfilePageComponent},
  {path: 'pageNotFound', component: PageNotFoundComponent},
  {path: 'unauthorized', component: UnauthorizedComponent}
];
