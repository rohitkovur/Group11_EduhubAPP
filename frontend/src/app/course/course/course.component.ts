import { Component } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { ActivatedRoute, Router } from '@angular/router';
import { StripeService } from '../../services/stripe.service';

@Component({
	selector: 'app-course',
	templateUrl: './course.component.html',
	styleUrl: './course.component.css'
})
export class CourseComponent {
	course: any;
	lessons: any = [];

	constructor(private courseService: CourseService, private route: ActivatedRoute,
		private router: Router, private stripeService: StripeService) {
		this.route.paramMap.subscribe((params: any) => {
			const courseId = params.get('courseId');
			if (courseId) {
				// this.course = this.courseService.getCourse(courseId);
				this.courseService.getCourse(courseId)
					.subscribe({
						next: (res) => {
							// console.log(res);
							this.course = res.course;
						},
						error: (e) => console.error(e),
						complete: () => console.info('complete')
					});
				this.courseService.getLessons(courseId)
					.subscribe({
						next: (res: any) => {
							console.log(res);
							this.course = res.course;
						},
						error: (e: any) => console.error(e),
						complete: () => console.info('complete')
					});

			}
		});
	}

	openLesson(lessonId: string) {
		this.router.navigate(['lesson', lessonId]);
	}

	async checkout(): Promise<void> {
		await this.stripeService.checkout(1000, this.course.id); // Amount in cents
	}

}