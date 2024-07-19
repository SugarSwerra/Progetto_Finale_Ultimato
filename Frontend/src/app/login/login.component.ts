import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {catchError, EMPTY, Observable, retry, timeout} from "rxjs";
import {FormsModule, NgForm} from "@angular/forms";
import {LoginRequest} from "../model/loginRequest";
import {NgClass, NgIf} from "@angular/common";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "../service/user/user.service";
import {UserDTO} from "../model/UserDTO";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgClass,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginRequest: LoginRequest = new LoginRequest();

  isLoggedIn:boolean = false;

  constructor(private userService: UserService, private router: Router) {
  }

  @Output()
  loginEvent : EventEmitter<boolean> = new EventEmitter<boolean>();

  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  public login(form: NgForm) {
    if (form.valid) {
        this.userService.login(this.loginRequest).subscribe((result: any) => {
          localStorage.setItem('access_token', result.token);
          console.log(result.token);
          localStorage.setItem('user_email', this.loginRequest.email);
          window.location.reload();
        })
      this.router.navigate(["/"]);
    }

  }

@Output() dataEmitter = new EventEmitter<{
  name: string,
  lastname: string,
}
>();

}

// onUpdateEmail($event: Event) {
//   console.log("Email inserita: "+ (<HTMLInputElement>$event.target).value);
// }
//
//
// onUpdatePassword($event: Event) {
//   console.log("Password inserita: "+ (<HTMLInputElement>$event.target).value);
// }


// submit() {
//   if (this.loginRequest.email == "prova" && this.loginRequest.password == "prova") {
//     this.loginEvent.emit(true);
//     this.isLoggedIn = true;
//   } else {
//     this.loginEvent.emit(false)
//   }
//
// }

// checkPassword($event: Event) {
//   // this.isButtonDisabled = !((<HTMLInputElement>$event.target).value == "prova");
// }

// ngOnInit(): void {
//  alert("You have 5 seconds to login");
//   setTimeout(()=> {
//     this.isButtonDisabled = true;
//     alert("Time has exipered, refresh the page")
//   },5000)
// }

