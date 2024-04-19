import { Component } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
	selector: 'app-course-list',
	templateUrl: './course-list.component.html',
	styleUrl: './course-list.component.css'
})
export class CourseListComponent {
	mine = false;
	enrolled = false;
	courses: any = [];

	constructor(private courseService: CourseService, private route: ActivatedRoute, public router: Router) {

		this.mine = (this.route.snapshot.routeConfig?.path) === 'courses/mine';
		this.enrolled = (this.route.snapshot.routeConfig?.path) === 'courses/enrolled';

		if (this.mine) {
			this.courses = this.courseService.getCoursesList({ mine: true });
		} if (this.enrolled) {
			this.courses = this.courseService.getCoursesList({ enrolled: true });
		} else {
			// this.courses = this.courseService.getCoursesList({ all: true });
			this.courseService.getCoursesList({ all: true })
				.subscribe({
					next: (v: any) => {
						console.log(v);
						// this.courses = v;
						return [];
					},
					error: (e: any) => console.error(e),
					complete: () => console.info('complete')
				})
		}
	}

	openCourse(courseId: string) {
		this.router.navigate(['/course', courseId]);
	}

	editCourse(courseId: string) {
		this.router.navigate(['edit', 'course', courseId]);
	}
}
