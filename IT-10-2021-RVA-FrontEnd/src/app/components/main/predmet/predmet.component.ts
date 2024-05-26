import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Predmet } from 'src/app/models/predmet';
import { Sud } from 'src/app/models/sud';
import { PredmetService } from 'src/app/services/predmet.service';
import { PredmetDijalogComponent } from '../../dialogs/predmet-dijalog/predmet-dijalog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-predmet',
  templateUrl: './predmet.component.html',
  styleUrls: ['./predmet.component.css']
})
export class PredmetComponent implements OnInit, OnDestroy {

    
  displayedColumns = ['id', 'brojPr', 'opis', 'datumPocetka', 'aktivan', 'sud', 'actions'];
  dataSource!:MatTableDataSource<Predmet>;
  subscription!:Subscription;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator;

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
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
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

  public applyFilter(filter:any){
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }

}



