import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../../services/student';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './student-form.html',
  styleUrls: ['./student-form.css'],
})
export class StudentForm {

  student: any = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];

    if (id) {
      this.studentService.getById(id).subscribe((data: any) => {
        this.student = data;
      });
    }
  }

  saveStudent() {
    if (this.student.id) {
      this.studentService.update(this.student.id, this.student)
        .subscribe(() => this.router.navigate(['/students']));
    } else {
      this.studentService.create(this.student)
        .subscribe(() => this.router.navigate(['/students']));
    }
  }
}
