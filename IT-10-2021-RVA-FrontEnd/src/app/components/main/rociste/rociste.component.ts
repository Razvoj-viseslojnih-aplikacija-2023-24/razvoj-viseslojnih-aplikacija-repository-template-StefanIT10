import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Predmet } from 'src/app/models/predmet';
import { Rociste } from 'src/app/models/rociste';
import { Ucesnik } from 'src/app/models/ucesnik';
import { RocisteService } from 'src/app/services/rociste.service';
import { RocisteDijalogComponent } from '../../dialogs/rociste-dijalog/rociste-dijalog.component';

@Component({
  selector: 'app-rociste',
  templateUrl: './rociste.component.html',
  styleUrls: ['./rociste.component.css']
})
export class RocisteComponent implements OnInit, OnDestroy{
      
  displayedColumns = ['id', 'datumRocista', 'sudnica', 'ucesnik', 'predmet', 'actions'];
  dataSource!:MatTableDataSource<Rociste>;
  subscription!:Subscription;

  constructor(private service:RocisteService, public dialog:MatDialog){}
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.service.getAllRociste().subscribe(
      (data) => {
        //console.log(data)
        this.dataSource = new MatTableDataSource(data);
      }    
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      
    }
    
  }

  public openDialog(flag:number, id?:number, datumRocista?:Date, sudnica?:string, ucesnik?:Ucesnik, predmet?:Predmet){
    const dialogRef = this.dialog.open(RocisteDijalogComponent, {data: {id, datumRocista, sudnica, ucesnik, predmet}});
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
