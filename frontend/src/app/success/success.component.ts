import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
	selector: 'app-success',
	templateUrl: './success.component.html',
	styleUrl: './success.component.css'
})
export class SuccessComponent {

	constructor(private route: ActivatedRoute, private router: Router) {
		this.route.paramMap.subscribe((params: any) => {
			const courseId = params.get('courseId');
			if (courseId) {
				// make API call to enroll student
				this.router.navigate(['course', courseId]);
			}
		});
	}
}
