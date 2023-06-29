import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    this.authService.register(this.username, this.password).subscribe({
      next: (response: any) => {
        // Store the token in local storage or a cookie
        console.log('Registered successfully');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        // Handle registration error
        console.error('Registration failed:', error);
      }
    });
  }
}
