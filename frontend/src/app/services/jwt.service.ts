import { Injectable } from '@angular/core';

@Injectable()
export class JwtService {
	public get type(): string {
		return window.localStorage['_type'];
	}

	public set type(value: string) {
		if (!value) {
			delete window.localStorage['_type'];
		} else {
			window.localStorage['_type'] = value.toLowerCase();
		}
	}

	public get userTemp(): string {
		return window.localStorage['_userTemp'];
	}

	public set userTemp(value: string) {
		if (!value) {
			delete window.localStorage['_userTemp'];
		} else {
			window.localStorage['_userTemp'] = value;
		}
	}

	getUserData() {
		let userData;
		try {
			userData = JSON.parse(window.localStorage['user']);
		} catch (error) {
			// Handle if user is undefined or JSON parsing error
			console.error('Error parsing user data:', error);
			userData = {};
		} return { emailId: userData.email, name: userData.name, role: "student", userId: userData.userId }
	}

	getToken(): string {
		const token = window.localStorage['jwtToken'];
		return ((token && typeof token !== 'undefined' && token !== 'undefined') ? token : '');
	}

	saveToken(token: string) {
		window.localStorage['jwtToken'] = token;
	}

	destroyToken() {
		window.localStorage.removeItem('jwtToken');
	}

	getUsername(): string {
		return window.localStorage['si_username'];
	}

	getRole(): string {
		const role = window.localStorage['role'];
		return ((role && typeof role !== 'undefined' && role !== 'undefined') ? role : '');
	}

	saveUsername(username: string, roleLevel: number = 0, role: string) {
		window.localStorage['role'] = role;
		window.localStorage['roleLevel'] = roleLevel;
		// Using toLowerCase to avoid any conflict in case when verifying jwt
		window.localStorage['si_username'] = username.toLowerCase();
	}

	destroyUsername() {
		window.localStorage.removeItem('role');
		window.localStorage.removeItem('roleLevel');
		window.localStorage.removeItem('si_username');
	}

	saveHash(hash: string) {
		window.localStorage['hashed_email_mobile'] = hash;
	}

	getHash(): string {
		const hash = window.localStorage['hashed_email_mobile'];
		return ((hash && typeof hash !== 'undefined' && hash !== 'undefined') ? hash : '');
	}

	removeHash() {
		return window.localStorage.removeItem('hashed_email_mobile');
	}

	// deprecated
	saveEmailOrMobile(token: string) {
		window.localStorage['si_email_mobile'] = token;
	}

	// deprecated
	getEmailOrMobile(): string {
		const val = window.localStorage['si_email_mobile'];
		return ((val && typeof val !== 'undefined' && val !== 'undefined') ? val : '');
	}

	// deprecated
	removeEmailOrMobile() {
		window.localStorage.removeItem('si_email_mobile');
	}

	getKey(key: string) {
		return window.localStorage[key];
	}

	setKey(key: string, value: string) {
		window.localStorage[key] = value;
	}

	setRC(value: string) {
		window.localStorage['rc'] = value;
	}

	getRC() {
		const rc = localStorage.getItem('rc');
		return ((rc && typeof rc !== 'undefined' && rc !== 'undefined') ? rc : '');
	}
}
