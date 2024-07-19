import { Component } from '@angular/core';
import {FormBuilder, FormsModule, NgForm} from "@angular/forms";
import {RegisterRequest} from "../model/registerRequest";
import {NgClass} from "@angular/common";
import {UserService} from "../service/user/user.service";
import {HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    NgClass
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerRequest: RegisterRequest = new RegisterRequest();

  // onUpdatePassword($event: Event) {
  //   console.log("Password inserita: "+ (<HTMLInputElement>$event.target).value);
  // }
  //
  // onUpdateEmail($event: Event) {
  //   console.log("Email inserita: " + (<HTMLInputElement>$event.target).value);
  // }
  //
  // onUpdateLastname($event: Event) {
  //   console.log("Cognome inserito: " +(<HTMLInputElement>$event.target).value);
  // }
  //
  // onUpdateName($event: Event) {
  //   console.log("Nome inserito: "+ (<HTMLInputElement>$event.target).value);
  // }

  constructor(private userService: UserService, private router: Router) {

  }



  register(form: NgForm) {
      if (form.valid) {
        this.userService.register(this.registerRequest).subscribe((result: any) => {
          this.router.navigate(["/"]);
        })
      }
  }
}
