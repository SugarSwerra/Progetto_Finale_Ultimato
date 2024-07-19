import { Component } from '@angular/core';
import {LoginComponent} from "../login/login.component";
import {NgForOf, NgIf} from "@angular/common";
import {RegisterComponent} from "../register/register.component";
import {SearchCourseComponent} from "../search-course/search-course.component";
import {ShopComponent} from "../shop/shop.component";
import {UserService} from "../service/user/user.service";
import {CorsoService} from "../service/corso/corso.service";
import {CorsoDto} from "../model/CorsoDto";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    LoginComponent,
    NgForOf,
    NgIf,
    RegisterComponent,
    SearchCourseComponent,
    ShopComponent,
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  title = 'standard';

  constructor(private userService: UserService, private corsoService: CorsoService) {
    this.userService.getAll().subscribe(result => {
      console.log(result);
    });
  }


  courseSearch: string = "";

  receiveData(searchText: string): void {
    this.courseSearch = searchText;
  }




  onLogin($event: boolean) {
    if($event) {
      this.title = "Login Succesfull"
    } else {
      this.title = "Unauthorized";
    }
  }

  courses: CorsoDto[] = [];

  ngOnInit() {
    this.getCorsi();
  }

  getCorsi(): void {
    this.corsoService.getAll().subscribe(data => {
      this.courses = data;
    })
  }
}
