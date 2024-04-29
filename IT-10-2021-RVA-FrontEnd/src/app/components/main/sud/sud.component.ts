import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { SudDijalogComponent } from 'src/app/component/dialogs/sud-dijalog/sud-dijalog.component';
import { Sud } from 'src/app/models/sud';
import { SudService } from 'src/app/services/sud.service';

@Component({
  selector: 'app-sud',
  templateUrl: './sud.component.html',
  styleUrls: ['./sud.component.css']
})
export class SudComponent implements OnInit, OnDestroy {

  displayedColumns = ['id', 'naziv', 'adresa', 'actions'];
  dataSource!:MatTableDataSource<Sud>;
  subscription!:Subscription;

  constructor(private service:SudService, public dialog:MatDialog){}
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.service.getAllSud().subscribe(
      (data) => {
        //console.log(data)
        this.dataSource = new MatTableDataSource(data);
      }    
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      
    }
    
  }

  public openDialog(flag:number, id?:number, naziv?:string, adresa?:string){
    const dialogRef = this.dialog.open(SudDijalogComponent, {data: {id, naziv, adresa}});
    dialogRef.componentInstance.flag = flag
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1){
          this.loadData()
        }
      }
    )
  }
}