import { Component, OnInit } from '@angular/core';
import { Article, ArticleService } from '../services/article.service';
import { CourseService } from '../services/course.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

	cards = [
		{
			title: 'Card 1',
			subtitle: 'Subtitle 1',
			content: 'Content for Card 1',
			imageUrl: 'https://v1.tailwindcss.com/img/card-top.jpg'
		},
		{
			title: 'Card 1',
			subtitle: 'Subtitle 1',
			content: 'Content for Card 1',
			imageUrl: 'https://v1.tailwindcss.com/img/card-top.jpg'
		},
		{
			title: 'Card 1',
			subtitle: 'Subtitle 1',
			content: 'Content for Card 1',
			imageUrl: 'https://v1.tailwindcss.com/img/card-top.jpg'
		},
		{
			title: 'Card 1',
			subtitle: 'Subtitle 1',
			content: 'Content for Card 1',
			imageUrl: 'https://v1.tailwindcss.com/img/card-top.jpg'
		},

		// Add more cards as needed
	];

	articles: Article[] = [];
	courses: any;

	constructor(private articleService: ArticleService, private courseService: CourseService, private router: Router) {
		this.articles = this.articleService.getArticlesList(false)
		// this.articleService.getArticlesList(this.mine)
		// 	.subscribe({
		// 		next: (v: any) => {
		// 			console.log(v);
		// 			this.articles = v;
		// 		},
		// 		error: (e: any) => console.error(e),
		// 		complete: () => console.info('complete')
		// 	})
		// this.courses = this.courseService.getCoursesList({ all: true });
		this.courses = this.courseService.getCoursesList({ all: true })
			.subscribe({
				next: (res: any) => {
					console.log(res);
					// this.courses = v;
					this.courses = res.courseList.slice(0, 5);
				},
				error: (e: any) => console.error(e),
				complete: () => console.info('complete')
			})
	}

	ngOnInit(): void {
		//   this.ArticleSerice.getCurrentUser().subscribe(user => {
		//     this.user = user;
		//   })
	}

	openArticle(articleId: string) {
		this.router.navigate(['/article', articleId]);
	}

	openCourse(courseId: string) {
		this.router.navigate(['/course', courseId]);
	}

	truncateTitle(title: string, limit: number): string {
		if (title.length > limit) {
			return title.substring(0, limit) + '...';
		} else {
			return title;
		}
	}

}
