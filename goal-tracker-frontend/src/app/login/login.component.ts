import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private  authService: AuthService, private router: Router) {}
  login() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response: any) => {
        // Handle successful login
        console.log('Logged in successfully');
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        // Handle login error
        console.error('Login failed:', error);
      }
    });
  }
}
