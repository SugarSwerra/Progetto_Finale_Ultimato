import { Component } from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {SearchCourseComponent} from "../search-course/search-course.component";
import {ShopComponent} from "../shop/shop.component";
import {UserService} from "../service/user/user.service";
import {CorsoService} from "../service/corso/corso.service";
import {CorsoDto} from "../model/CorsoDto";

@Component({
  selector: 'app-corsi',
  standalone: true,
    imports: [
        NgForOf,
        NgIf,
        SearchCourseComponent,
        ShopComponent
    ],
  templateUrl: './corsi.component.html',
  styleUrl: './corsi.component.css'
})
export class CorsiComponent {

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
