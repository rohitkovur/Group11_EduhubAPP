import { Component } from '@angular/core';
import { ArticleService, Article } from '../../services/article.service';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
	selector: 'app-article-list',
	templateUrl: './article-list.component.html',
	styleUrl: './article-list.component.css'
})
export class ArticleListComponent {
	mine = false;;
	articles: Article[] = [];

	constructor(private articleService: ArticleService, private route: ActivatedRoute, public router: Router) {

		this.mine = (this.route.snapshot.routeConfig?.path) === 'articles/mine';

		this.articles = this.articleService.getArticlesList(this.mine);

		// this.articleService.getArticlesList(this.mine)
		// 	.subscribe({
		// 		next: (v: any) => {
		// 			console.log(v);
		// 			this.articles = v;
		// 		},
		// 		error: (e: any) => console.error(e),
		// 		complete: () => console.info('complete')
		// 	})
	}

	openArticle(articleId: string) {
		this.router.navigate(['/article', articleId]);
	}

	editArticle(articleId: string) {
		this.router.navigate(['edit', 'article', articleId]);
	}

}
