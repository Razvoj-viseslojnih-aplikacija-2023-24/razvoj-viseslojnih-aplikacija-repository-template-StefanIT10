import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Predmet } from 'src/app/models/predmet';
import { Sud } from 'src/app/models/sud';
import { PredmetService } from 'src/app/services/predmet.service';
import { SudService } from 'src/app/services/sud.service';

@Component({
  selector: 'app-predmet-dijalog',
  templateUrl: './predmet-dijalog.component.html',
  styleUrls: ['./predmet-dijalog.component.css']
})
export class PredmetDijalogComponent implements OnInit {
  flag!:number;
  sudovi!: Sud[];

  constructor (
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Predmet>,
    @Inject (MAT_DIALOG_DATA) public data:Predmet,
    public service: PredmetService,
    public sudService: SudService
  ){}

  ngOnInit(): void {
    this.sudService.getAllSud().subscribe(
      (data) => {
        this.sudovi = data;
      }
    )
  }

  public compare(a:Predmet, b:Predmet){
    return a.id == b.id;
  }
  
  public add(){
    this.service.createPredmet(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno dodat predmet sa ID: ${data.id}`, `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration: 1500});
    }
  }

  public update(){
    this.service.updatePredmet(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno azuriran predmet sa ID: ${data.id}`, `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno azuriran`, `Zatvori`, {duration: 1500});
    }
  }

  public delete(){
    this.service.deletePredmet(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`${data}`,
        `U redu`, {duration: 2500})
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno brisanje`, `Zatvori`, {duration: 1500});
    }
  }


  public cancel(){
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmena`, `Zatvori`, {duration:1500});
  }
}
