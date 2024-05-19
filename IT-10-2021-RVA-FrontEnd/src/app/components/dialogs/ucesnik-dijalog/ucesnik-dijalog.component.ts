import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Ucesnik } from 'src/app/models/ucesnik';
import { UcesnikService } from 'src/app/services/ucesnik.service';

@Component({
  selector: 'app-ucesnik-dijalog',
  templateUrl: './ucesnik-dijalog.component.html',
  styleUrls: ['./ucesnik-dijalog.component.css']
})
export class UcesnikDijalogComponent {
  flag!:number;

  constructor (
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Ucesnik>,
    @Inject (MAT_DIALOG_DATA) public data:Ucesnik,
    public service: UcesnikService
  ){}
  
  public add(){
    this.service.createUcesnik(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Ucesnik sa prezimenom ${data.prezime} je uspesno dodat`,
        `U redu`, {duration: 2500})
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration: 1500});
    }
  }

  public update(){
    this.service.updateUcesnik(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Ucesnik sa prezimenom ${data.prezime} je uspesno azuriran`,
        `U redu`, {duration: 2500})
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno azuriran`, `Zatvori`, {duration: 1500});
    }
  }

  public delete(){
    this.service.deleteUcesnik(this.data.id).subscribe(
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
