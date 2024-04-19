import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';

@Component({
	selector: 'app-quiz-form',
	templateUrl: './quiz-form.component.html',
	styleUrls: ['./quiz-form.component.css']
})
export class QuizFormComponent implements OnInit {

	quizForm: FormGroup | any;

	constructor(private fb: FormBuilder) { }

	ngOnInit(): void {
		this.quizForm = this.fb.group({
			questions: this.fb.array([
				this.createQuestion()
			])
		});
	}

	createQuestion(): FormGroup {
		return this.fb.group({
			question: ['', Validators.required],
			option1: ['', Validators.required],
			option2: ['', Validators.required],
			option3: ['', Validators.required],
			option4: ['', Validators.required],
			correctAnswer: ['', Validators.required]
		});
	}

	addQuestion(): void {
		const questions = this.quizForm.get('questions') as FormArray;
		questions.push(this.createQuestion());
	}

	removeQuestion(index: number): void {
		const questions = this.quizForm.get('questions') as FormArray;
		questions.removeAt(index);
	}

	onSubmit() {
		if (this.quizForm.valid) {
			// Process the form data here
			console.log(this.quizForm.value);
		}
	}
}
