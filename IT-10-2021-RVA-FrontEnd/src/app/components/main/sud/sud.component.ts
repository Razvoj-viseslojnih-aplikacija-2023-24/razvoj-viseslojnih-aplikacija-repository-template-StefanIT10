import { Component, OnInit } from '@angular/core';
import { SudService } from 'src/app/services/sud.service';

@Component({
  selector: 'app-sud',
  templateUrl: './sud.component.html',
  styleUrls: ['./sud.component.css']
})
export class SudComponent implements OnInit {

  constructor(private service:SudService){}
  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.service.getAllSud().subscribe(
      (data) => {
        console.log(data)
      }    
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      
    }
    
  }

}
