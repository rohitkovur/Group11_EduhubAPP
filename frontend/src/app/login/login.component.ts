import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { SocialAuthService } from '@abacritt/angularx-social-login';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	loggedIn: boolean = false;

	constructor(private authService: AuthService, private socialAuthService: SocialAuthService, private router: Router) { }


	ngOnInit() {
		if (this.authService.isAuthenticated()) {
			this.router.navigate(['/home']);
		}
		this.socialAuthService.authState.subscribe(user => {
			this.router.navigate(['/home'])
		})
	}

}
