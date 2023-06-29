import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment.development';

interface LoginResposne {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiServerUrl = environment.apiBaseUrl;
  
  private isLoggedIn = false;
  
  constructor(private http: HttpClient) {}
  

  login(username: string, password: string): Observable<any> {
    const loginData = { username, password };
    return this.http.post<LoginResposne>(`${this.apiServerUrl}/login`, loginData).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        this.isLoggedIn = true;
      })
    );
  }

  register(username: string, password: string): Observable<any> {
    const registrationData = { username, password };
    this.isLoggedIn = false;
    return this.http.post(`${this.apiServerUrl}/register`, registrationData);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getLoggedInStatus(): boolean {
    return this.isLoggedIn;
  }

}
