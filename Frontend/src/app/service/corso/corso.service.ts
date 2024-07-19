import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";
import {CorsoDto} from "../../model/CorsoDto";

@Injectable({
  providedIn: 'root'
})
export class CorsoService {

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<CorsoDto[]> {
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders({ 'Authorization': `Bearer ${token}` });
    return this.http.get<CorsoDto[]>("http://localhost:8080/api/corso", {headers}).pipe(retry(3), catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred: ' +error.error);
    } else {
      console.error(`Backend returned error: ${error.status}, body was: `, error.error);
    }
    return throwError(() => new Error('Something went wrong'));
  }
}
