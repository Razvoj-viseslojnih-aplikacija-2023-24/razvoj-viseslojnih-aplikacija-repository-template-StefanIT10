import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sud } from 'src/app/models/sud';
import { SudService } from 'src/app/services/sud.service';

@Component({
  selector: 'app-sud-dijalog',
  templateUrl: './sud-dijalog.component.html',
  styleUrls: ['./sud-dijalog.component.css']
})
export class SudDijalogComponent {
  flag!:number;

  constructor (
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Sud>,
    @Inject (MAT_DIALOG_DATA) public data:Sud,
    public service: SudService
  ){}
  
  public add(){
    this.service.createSud(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Sud sa nazivom ${data.naziv} je uspesno dodat`,
        `U redu`, {duration: 2500})
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration: 1500});
    }
  }

  public update(){
    this.service.updateSud(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Sud sa nazivom ${data.naziv} je uspesno azuriran`,
        `U redu`, {duration: 2500})
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno azuriran`, `Zatvori`, {duration: 1500});
    }
  }

  public delete(){
    this.service.deleteSud(this.data.id).subscribe(
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
