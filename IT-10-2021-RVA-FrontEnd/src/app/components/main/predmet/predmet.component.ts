import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Predmet } from 'src/app/models/predmet';
import { Sud } from 'src/app/models/sud';
import { PredmetService } from 'src/app/services/predmet.service';
import { PredmetDijalogComponent } from '../../dialogs/predmet-dijalog/predmet-dijalog.component';

@Component({
  selector: 'app-predmet',
  templateUrl: './predmet.component.html',
  styleUrls: ['./predmet.component.css']
})
export class PredmetComponent implements OnInit, OnDestroy {

    
  displayedColumns = ['id', 'brojPr', 'opis', 'datumPocetka', 'aktivan', 'sud', 'actions'];
  dataSource!:MatTableDataSource<Predmet>;
  subscription!:Subscription;

  parentSelectedPredmet!:Predmet;

  constructor(private service:PredmetService, public dialog:MatDialog){}
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.service.getAllPredmet().subscribe(
      (data) => {
        //console.log(data)
        this.dataSource = new MatTableDataSource(data);
      }    
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      
    }
    
  }

  public openDialog(flag:number, id?:number, brojPr?:string, opis?:string, datumPocetka?:Date, aktivan?:boolean, sud?:Sud ){
    const dialogRef = this.dialog.open(PredmetDijalogComponent, {data: {id, brojPr, opis, datumPocetka, aktivan, sud }});
    dialogRef.componentInstance.flag = flag
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1){
          this.loadData()
        }
      }
    )
  }

  public selectRow(row:Predmet){
    this.parentSelectedPredmet = row;
  }  

}



