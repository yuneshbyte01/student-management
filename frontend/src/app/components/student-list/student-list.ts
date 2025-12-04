import { Component } from '@angular/core';
import { StudentService } from '../../services/student';
import { NgFor } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-student-list',
  standalone: true,
  templateUrl: './student-list.html',
  styleUrls: ['./student-list.css'],
  imports: [NgFor, RouterModule]
})
export class StudentList {

  students: any[] = [];

  constructor(private studentService: StudentService) { }

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.studentService.getAll().subscribe((data: any) => {
      this.students = data;
    });
  }

  deleteStudent(id: number) {
    this.studentService.delete(id).subscribe(() => {
      this.loadStudents(); // reload table
    });
  }

}
