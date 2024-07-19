import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {CarouselComponent} from "./carousel/carousel.component";
import {ShopComponent} from "./shop/shop.component";
import {FooterComponent} from "./footer/footer.component";
import {HomeComponent} from "./home/home.component";
import {NgForOf, NgIf} from "@angular/common";
import {Course} from "./model/Course";
import {interval} from "rxjs";
import {UserService} from "./service/user/user.service";
import {CorsoService} from "./service/corso/corso.service";
import {CorsoDto} from "./model/CorsoDto";
import {SearchCourseComponent} from "./search-course/search-course.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, RegisterComponent, NavbarComponent, CarouselComponent, ShopComponent, FooterComponent, HomeComponent, NgForOf, NgIf, SearchCourseComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'standard';

}
