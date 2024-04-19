import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
	providedIn: 'root'
})
export class AuthGuard implements CanActivate {

	constructor(private authService: AuthService, private router: Router, private apiSerive: ApiService) { }

	canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

		if (this.authService.isAuthenticated()) {
			return true;
		} else {
			this.router.navigate(['/login'], {
				queryParams: { returnUrl: state.url },
				queryParamsHandling: 'merge'
			});
			return false; // User is not authenticated, redirect to login page
		}
	}

}
