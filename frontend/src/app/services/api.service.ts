
import { throwError as observableThrowError, Observable, of, throwError } from 'rxjs';

import { catchError, retryWhen, mergeMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


import { JwtService } from './jwt.service';

@Injectable()
export class ApiService {
	constructor(
		private httpClient: HttpClient,
		private jwtService: JwtService
	) { }


	private formatErrors(response: any) {
		return observableThrowError(response.error);
	}

	private setHeaders(): HttpHeaders {
		const headersConfig = {
			'Content-Type': 'application/json',
			'Accept': 'application/json',

		};
		return new HttpHeaders(headersConfig);
	}

	get(path: string, params: HttpParams = new HttpParams()): Observable<any> {
		let userData = this.jwtService.getUserData();
		console.log(userData)
		params = params.set('userId', userData.userId);
		// params = params.set('name', userData.name);
		// params = params.set('role', userData.role);
		return this.httpClient.get<any>(
			path,
			{ headers: this.setHeaders(), params }
		).pipe(
			catchError(this.formatErrors)
		);
	}

	put(path: string, body: Object = {}): Observable<any> {
		let userData = this.jwtService.getUserData();
		body = { ...body, ...userData }

		return this.httpClient.put<any>(
			path,
			JSON.stringify(body),
			{ headers: this.setHeaders() }
		).pipe(
			catchError(this.formatErrors)
		);
	}

	post(path: string, body: Object = {}): Observable<any> {
		if (!path.includes('user/new')) {
			let userData = this.jwtService.getUserData();
			body = { ...body, ...userData }
		}

		return this.httpClient.post<any>(
			path,
			JSON.stringify(body),
			{ headers: this.setHeaders(), }
		).pipe(
			catchError(this.formatErrors)
		);
	}

	postFile(path: string, file: FormData): Observable<any> {
		let userData = this.jwtService.getUserData();
		file.append('emailId', userData.emailId);

		return this.httpClient.post<any>(
			path,
			file,
			{ headers: this.setHeaders(), }
		).pipe(
			catchError(this.formatErrors)
		);
	}

	delete(path: any, params: HttpParams = new HttpParams()): Observable<any> {
		let userData = this.jwtService.getUserData();
		params = params.set('userId', userData.userId);

		return this.httpClient.delete(
			path,
			{ headers: this.setHeaders(), params }
		).pipe(
			catchError(this.formatErrors)
		);
	}
}
