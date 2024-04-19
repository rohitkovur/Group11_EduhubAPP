import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SocialAuthService, SocialUser, GoogleLoginProvider } from '@abacritt/angularx-social-login';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiService } from './api.service';


@Injectable({
	providedIn: 'root'
})
export class AuthService {

	public targetUrl = `${environment.api_url}user/new`;


	private userSubject = new BehaviorSubject<SocialUser | null>(null); // BehaviorSubject to emit user data
	user$ = this.userSubject.asObservable(); // Observable for subscribing to user data

	constructor(private authService: SocialAuthService, private router: Router, private apiService: ApiService) {
		this.authService.authState.subscribe(user => {

			this.userSubject.next(user); // Emit user data when auth state changes
			if (user) {
				this.apiService.post(this.targetUrl, { ...user, emailId: user.email })
					.subscribe({
						next: (v) => {
							console.log(v);
							this.setItemInLocalStorage('user', JSON.stringify({ ...user, emailId: user.email, userId: v.userId }));
						},
						error: (e) => console.error(e),
						complete: () => console.info('complete')
					})
			} else {
				this.removeItemFromLocalStorage('user');
			}
		});

		// Initialize userSubject with the current user if available in localStorage
		const currentUserString = this.getItemFromLocalStorage('user');
		if (currentUserString) {
			const user = JSON.parse(currentUserString);
			this.userSubject.next(user);
		}
	}

	private getItemFromLocalStorage(key: string): string | null {
		return typeof window !== 'undefined' ? localStorage.getItem(key) : null;
	}

	private setItemInLocalStorage(key: string, value: string): void {
		if (typeof window !== 'undefined') {
			localStorage.setItem(key, value);
		}
	}

	private removeItemFromLocalStorage(key: string): void {
		if (typeof window !== 'undefined') {
			localStorage.removeItem(key);
		}
	}

	// Logout method
	logout() {
		this.authService.signOut()
			.then(() => {
			})
			.catch(error => {
				console.error('Logout failed:', error);
			});
		this.userSubject.next(null); // Emit null when user logs out
		this.removeItemFromLocalStorage('user');
		this.router.navigate(['/login']);
	}

	refreshToken(): void {
		this.authService.refreshAuthToken(GoogleLoginProvider.PROVIDER_ID);
	}

	// Check if user is authenticated
	isAuthenticated(): boolean {
		const user = this.userSubject.getValue();
		if (!user) {
			this.refreshToken();
			return false;
		}
		return true;
	}
}
