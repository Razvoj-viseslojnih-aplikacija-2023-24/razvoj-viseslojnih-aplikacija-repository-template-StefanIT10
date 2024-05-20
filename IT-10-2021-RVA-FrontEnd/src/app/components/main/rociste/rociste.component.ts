import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
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
export class RocisteComponent implements OnInit, OnDestroy, OnChanges{
      
  displayedColumns = ['id', 'datumRocista', 'sudnica', 'ucesnik', 'actions'];
  dataSource!:MatTableDataSource<Rociste>;
  subscription!:Subscription;

  @Input()
  childSelectedPredmet!:Predmet;

  constructor(private service:RocisteService, public dialog:MatDialog){}
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }


  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.service.getRocisteByPredmet(this.childSelectedPredmet.id).subscribe(
      (data) => {
        //console.log(data)
        this.dataSource = new MatTableDataSource(data);
      }    
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      
    }
    
  }

  public openDialog(flag:number, id?:number, datumRocista?:Date, sudnica?:string, ucesnik?:Ucesnik){
    const dialogRef = this.dialog.open(RocisteDijalogComponent, {data: {id, datumRocista, sudnica, ucesnik}});
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
