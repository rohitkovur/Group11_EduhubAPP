import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../../services/course.service';

@Component({
	selector: 'app-lesson',
	templateUrl: './lesson.component.html',
	styleUrl: './lesson.component.css'
})
export class LessonComponent {
	course: any;
	lesson: any;
	show = false;

	constructor(private courseService: CourseService, private route: ActivatedRoute) {
		this.route.paramMap.subscribe((params: any) => {
			const lessonId = params.get('lessonId');
			if (lessonId) {
				this.lesson = this.courseService.getLesson(lessonId);
				this.course = this.courseService.getCourse(this.lesson.courseId);
				this.show = true;
			}
		});
	}
}
