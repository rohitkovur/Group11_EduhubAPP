import { Component, OnDestroy } from '@angular/core';
import { Editor } from 'ngx-editor';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Article, ArticleService } from '../../services/article.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-article-form',
	templateUrl: './article-form.component.html',
	styleUrls: ['./article-form.component.css']
})
export class ArticleFormComponent implements OnDestroy {
	postForm: FormGroup | undefined;
	editor: Editor;
	isEdit = false;
	article: Article | undefined;

	constructor(private formBuilder: FormBuilder, private articleService: ArticleService,
		private router: Router, private route: ActivatedRoute) {
		this.editor = new Editor();

		if ((this.route.snapshot.routeConfig?.path) === 'edit/article/:articleId') {
			this.isEdit = true;
		}

		if (this.isEdit) {
			this.route.paramMap.subscribe((params: any) => {
				const articleId = params.get('articleId');
				if (articleId) {
					this.article = this.articleService.getArticle(articleId);
				}
				this.postForm = this.formBuilder.group({
					title: [this.article?.title, Validators.required],
					subtitle: [this.article?.subtitle], // Optional field
					imageUrl: [this.article?.imageUrl], // Optional field
					content: [this.article?.description, Validators.required]
				});
			});
		} else {
			this.postForm = this.formBuilder.group({
				title: ['', Validators.required],
				subtitle: [''], // Optional field
				imageUrl: [''], // Optional field
				content: ['', Validators.required]
			});
		}

	}

	onSubmit() {
		if (this.isEdit) {
			this.articleService.editArticle(this.article, this.postForm?.value)
				.subscribe({
					next: (v) => {
						console.log(v);
						this.router.navigate(['/articles/mine']);
					},
					error: (e) => console.error(e),
					complete: () => console.info('complete')
				})
		} else {
			this.articleService.createArticle(this.postForm?.value)
				.subscribe({
					next: (v) => {
						console.log(v);
						this.router.navigate(['/articles/mine']);
					},
					error: (e) => console.error(e),
					complete: () => console.info('complete')
				})
		}
		// this.postForm.reset();
	}

	// Make sure to destroy the editor
	ngOnDestroy(): void {
		this.editor.destroy();
	}
}
