import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CourseService } from '../../services/course.service';

@Component({
    selector: 'app-course-form',
    templateUrl: './course-form.component.html',
    styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent {
    postForm: FormGroup | undefined;
    isEdit = false;
    course: any;
    lessons: any = [];

    constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, private courseService: CourseService) {

        if ((this.route.snapshot.routeConfig?.path) === 'edit/course/:courseId') {
            this.isEdit = true;
        }

        if (this.isEdit) {
            this.route.paramMap.subscribe((params: any) => {
                const courseId = params.get('courseId');
                if (courseId) {
                    this.course = this.courseService.getCourse(courseId);
                    this.lessons = this.courseService.getLessons(courseId);
                }
                this.postForm = this.formBuilder.group({
                    title: [this.course?.title, Validators.required],
                    subtitle: [this.course?.subtitle], // Optional field
                    imageUrl: [this.course?.imageUrl], // Optional field
                    description: [this.course?.description, Validators.required],
                    coursePrice: [this.course?.coursePrice, Validators.required],
                });
            });
        } else {
            this.postForm = this.formBuilder.group({
                title: ['', Validators.required],
                subtitle: [''], // Optional field
                imageUrl: [''], // Optional field
                description: ['', Validators.required],
                coursePrice: ['', Validators.required],
            });
        }

    }

    onSubmit() {
        if (this.isEdit) {
            this.courseService.editCourse(this.course, this.postForm?.value);
            // this.courseService.editCourse(this.course, this.postForm?.value)
            // .subscribe({
            //     next: (v) => {
            //         console.log(v);
            //         this.router.navigate(['/courses/mine']);
            //     },
            //     error: (e) => console.error(e),
            //     complete: () => console.info('complete')
            // })
        } else {
            this.courseService.createCourse(this.postForm?.value);
            // this.courseService.createCourse(this.postForm?.value)
            // .subscribe({
            //     next: (v) => {
            //         console.log(v);
            //         this.router.navigate(['/courses/mine']);
            //     },
            //     error: (e) => console.error(e),
            //     complete: () => console.info('complete')
            // })

        }
        // this.postForm.reset();
    }

    addLesson() {
        this.router.navigate(['create', this.course.id, 'lesson']);
    }

    goToLesson(lessonId: any) {
        this.router.navigate(['lesson', lessonId]);
    }

    editLesson(lessonId: any) {
        this.router.navigate(['edit/lesson', lessonId]);
    }
}
