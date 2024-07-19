import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-search-course',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './search-course.component.html',
  styleUrl: './search-course.component.css'
})
export class SearchCourseComponent {

  @Output() cerca = new EventEmitter<string>();
  searchText: string = "";

  onSearchTextChange() {
    this.cerca.emit(this.searchText);
  }
}
