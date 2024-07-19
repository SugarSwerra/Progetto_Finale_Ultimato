import {Component, Input, Output} from '@angular/core';
import {UserDTO} from "../model/UserDTO";
import {UserService} from "../service/user/user.service";
import {NgIf} from "@angular/common";
import {Router, RouterLink} from "@angular/router";
import {ProfileComponent} from "../profile/profile.component";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgIf,
    RouterLink,
    ProfileComponent
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private userService: UserService, private router: Router) {
  }





  @Input()
  inputParam: string = "";


  isLogged(): boolean {
    return (localStorage.getItem("access_token") != null);
  }

  public logout():void {
    localStorage.removeItem('access_token');
    localStorage.removeItem('user_email');
    this.router.navigate(["/"]);
  }

}
