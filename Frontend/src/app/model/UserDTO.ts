import {Course} from "./Course";

export class UserDTO {
  id: number;
  nome: string;
  cognome: string;
  email: string;
  password: string;
  corsi: Course[];
}
