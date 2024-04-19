import { Component, } from '@angular/core';
import { ArticleService, Article } from '../../services/article.service';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-article',
	templateUrl: './article.component.html',
	styleUrl: './article.component.css'
})
export class ArticleComponent {
	article: Article | undefined;

	constructor(private articleService: ArticleService, private route: ActivatedRoute) {
		this.route.paramMap.subscribe((params: any) => {
			const articleId = params.get('articleId');
			if (articleId) {
				this.article = this.articleService.getArticle(articleId);
				// this.articleService.getArticle(articleId)
				// .subscribe({
				// 	next: (v:any) => {
				// 		console.log(v);
				// 		this.article = v;
				// 	},
				// 	error: (e:any) => console.error(e),
				// 	complete: () => console.info('complete')
				// })
			}
		});
	}
}
