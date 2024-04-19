import { NgModule } from '@angular/core';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { CourseComponent } from './course/course/course.component';
import { CourseFormComponent } from './course/course-form/course-form.component';
import { LessonComponent } from './lesson/lesson/lesson.component';
import { LessonFormComponent } from './lesson/lesson-form/lesson-form.component';
import { QuizComponent } from './quiz/quiz/quiz.component';
import { QuizFormComponent } from './quiz/quiz-form/quiz-form.component';
import { ArticleComponent } from './article/article/article.component';
import { ArticleFormComponent } from './article/article-form/article-form.component';
import { ArticleListComponent } from './article/article-list/article-list.component';
import { CourseListComponent } from './course/course-list/course-list.component';


import { SocialLoginModule, SocialAuthServiceConfig, } from '@abacritt/angularx-social-login';
import {
	GoogleLoginProvider, GoogleSigninButtonModule
} from '@abacritt/angularx-social-login';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { NgxEditorModule } from 'ngx-editor';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { StripeModule } from "stripe-angular";
import { SuccessComponent } from './success/success.component'
import { ApiService } from './services/api.service';
import { JwtService } from './services/jwt.service';




const rootRouting = RouterModule.forRoot([], { scrollPositionRestoration: 'enabled' });


@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		HomeComponent,
		HeaderComponent,
		CourseComponent,
		CourseFormComponent,
		LessonComponent,
		LessonFormComponent,
		QuizComponent,
		QuizFormComponent,
		ArticleComponent,
		ArticleFormComponent,
		ArticleListComponent,
		CourseListComponent,
		SuccessComponent,
	],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		rootRouting,
		AppRoutingModule,
		FormsModule,
		ReactiveFormsModule,
		HttpClientModule,

		SocialLoginModule,
		GoogleSigninButtonModule,
		StripeModule.forRoot(""),

		MatToolbarModule,
		MatCardModule,
		MatInputModule,
		NgxEditorModule,
		MatFormFieldModule,
		MatButtonModule,
	],
	providers: [
		provideClientHydration(),
		{
			provide: 'SocialAuthServiceConfig',
			useValue: {
				autoLogin: false,
				providers: [
					{
						id: GoogleLoginProvider.PROVIDER_ID,
						provider: new GoogleLoginProvider('773354218448-doa35u3ovua255hvu92ffo8uv32c5njl.apps.googleusercontent.com') // your client id
					}
				],
				onError: (err) => {
					console.error(err);
				}
			} as SocialAuthServiceConfig,
		},
		ApiService,
		JwtService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
