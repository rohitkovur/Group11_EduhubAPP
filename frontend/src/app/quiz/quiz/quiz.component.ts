import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

export interface QuizQuestion {
	question: string;
	option1: string;
	option2: string;
	option3: string;
	option4: string;
	correctAnswer: string;
}

@Component({
	selector: 'app-quiz',
	templateUrl: './quiz.component.html',
	styleUrl: './quiz.component.css'
})
export class QuizComponent {

	@Input() questions: QuizQuestion[] = [
		{
			"question": "sdfndfgdf,ag s,",
			"option1": "sdfmlsdnfkj ",
			"option2": "lksndfksdn",
			"option3": "lsfnkwsnf",
			"option4": "sldnfksnf",
			"correctAnswer": "sdfmlsdnfkj "
		},
		{
			"question": "sdfnk g erlgnerg erlg wel dfk vlf ",
			"option1": "sdfksdf wef",
			"option2": "k",
			"option3": "j",
			"option4": "knk",
			"correctAnswer": "j"
		}
	];
	quizForm: FormGroup | any;
	currentQuestionIndex = 0;
	submitted = false;

	constructor(private fb: FormBuilder) { }

	ngOnInit(): void {
		this.quizForm = this.fb.group({
			answer: ['', Validators.required]
		});
	}

	nextQuestion(): void {
		if (this.currentQuestionIndex < this.questions.length - 1) {
			this.currentQuestionIndex++;
			this.quizForm.reset();
		} else {
			this.submitted = true;
		}
	}

	get currentQuestion(): QuizQuestion {
		return this.questions[this.currentQuestionIndex];
	}

	onSubmit(): void {
		// Submit logic here
		if (this.quizForm.valid) {
			// Check answer and handle scoring/logic
			this.nextQuestion();
		}
	}
}

