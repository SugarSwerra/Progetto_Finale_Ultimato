import { Component } from '@angular/core';
import {UserDTO} from "../model/UserDTO";
import {UserService} from "../service/user/user.service";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  constructor(private userService: UserService) {
  }

  nome_completo: string = "";

  ngOnInit(): void {
    this.userService.findUserByMail(localStorage.getItem("user_email")).subscribe((user: UserDTO) =>{
      if (user.nome == null || user.cognome == null) {
        this.nome_completo = "";
      } else {
        this.nome_completo = user.nome + " " + user.cognome;
      }
    })
  }

}
