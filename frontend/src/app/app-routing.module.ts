import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './services/auth.guard.service';
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
import { SuccessComponent } from './success/success.component';

const routes: Routes = [
  // {
  // 	path: 'editprofile', loadChildren: () =>
  // 		import('./editprofile/editprofile.module').then((m) => m.EditprofileModule)
  // },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },

  { path: 'course/:courseId', component: CourseComponent, canActivate: [AuthGuard] },
  { path: 'article/:articleId', component: ArticleComponent, canActivate: [AuthGuard] },
  { path: 'lesson/:lessonId', component: LessonComponent, canActivate: [AuthGuard] },
  { path: 'quiz/:quizId', component: QuizComponent, canActivate: [AuthGuard] },

  { path: 'create/course', component: CourseFormComponent, canActivate: [AuthGuard] },
  { path: 'create/article', component: ArticleFormComponent, canActivate: [AuthGuard] },
  { path: 'create/:courseId/lesson', component: LessonFormComponent, canActivate: [AuthGuard] },
  { path: 'create/:courseId/quiz', component: QuizFormComponent, canActivate: [AuthGuard] },

  { path: 'edit/course/:courseId', component: CourseFormComponent, canActivate: [AuthGuard] },
  { path: 'edit/article/:articleId', component: ArticleFormComponent, canActivate: [AuthGuard] },
  { path: 'edit/lesson/:lessonId', component: LessonFormComponent, canActivate: [AuthGuard] },
  { path: 'edit/quiz/:quizId', component: QuizFormComponent, canActivate: [AuthGuard] },

  { path: 'articles/mine', component: ArticleListComponent, canActivate: [AuthGuard] },
  { path: 'courses/mine', component: CourseListComponent, canActivate: [AuthGuard] },
  { path: 'success/:courseId', component: SuccessComponent, canActivate: [AuthGuard] },


  { path: '**', redirectTo: '' }

  /**
   * my courses ????
   */

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

