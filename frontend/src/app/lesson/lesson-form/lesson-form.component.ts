import { Component } from '@angular/core';
import { Editor } from 'ngx-editor';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CourseService } from '../../services/course.service';
@Component({
	selector: 'app-lesson-form',
	templateUrl: './lesson-form.component.html',
	styleUrl: './lesson-form.component.css'
})
export class LessonFormComponent {
	postForm: FormGroup | undefined;
	editor: Editor;
	course: any;
	isEdit = false;
	lesson: any;

	constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, private courseService: CourseService) {
		this.editor = new Editor();
		if ((this.route.snapshot.routeConfig?.path) === 'edit/lesson/:lessonId') {
			this.isEdit = true;
		}

		this.route.paramMap.subscribe((params: any) => {
			const lessonId = params.get('lessonId');
			this.lesson = this.courseService.getLesson(lessonId);
			this.course = this.courseService.getCourse(this.lesson.courseId);
			if (this.isEdit) {
				this.postForm = this.formBuilder.group({
					title: [this.lesson?.title, Validators.required],
					subtitle: [this.lesson?.subtitle], // Optional field
					imageUrl: [this.lesson?.imageUrl], // Optional field
					content: [this.lesson?.description, Validators.required]
				});
			} else {
				this.postForm = this.formBuilder.group({
					title: ['', Validators.required],
					subtitle: [''], // Optional field
					imageUrl: [''], // Optional field
					content: ['', Validators.required]
				});
			}
		})

	}

	onSubmit() {
		if (this.isEdit) {
			this.courseService.updateLesson(this.lesson, this.postForm?.value);
		} else {
			this.courseService.createLesson(this.course.id, this.postForm?.value);
		}
		// this.postForm.reset();
		this.router.navigate(['/edit/course', this.course.id]);
	}

}
