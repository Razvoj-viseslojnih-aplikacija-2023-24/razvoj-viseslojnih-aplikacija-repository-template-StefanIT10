import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-ucesnik',
  templateUrl: './ucesnik.component.html',
  styleUrls: ['./ucesnik.component.css']
})
export class UcesnikComponent {

  displayedColumns = ['id', 'ime', 'prezime', 'mbr', 'status'];
  dataSource!:MatTableDataSource<Ucesnik>;
  subscription!:Subscription;

  constructor(private service:ucesnikService, public dialog:MatDialog){}
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.loadData();

}
