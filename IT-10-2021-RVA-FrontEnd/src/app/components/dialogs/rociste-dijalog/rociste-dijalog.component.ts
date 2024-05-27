import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Predmet } from 'src/app/models/predmet';
import { Rociste } from 'src/app/models/rociste';
import { Ucesnik } from 'src/app/models/ucesnik';
import { PredmetService } from 'src/app/services/predmet.service';
import { RocisteService } from 'src/app/services/rociste.service';
import { UcesnikService } from 'src/app/services/ucesnik.service';

@Component({
  selector: 'app-rociste-dijalog',
  templateUrl: './rociste-dijalog.component.html',
  styleUrls: ['./rociste-dijalog.component.css']
})

export class RocisteDijalogComponent{
  flag!:number;
  ucesnici!: Ucesnik[];


  constructor (
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Rociste>,
    @Inject (MAT_DIALOG_DATA) public data:Rociste,
    public service: RocisteService,
    public ucesnikService: UcesnikService
  ){}


  ngOnInit(): void {
    this.ucesnikService.getAllUcesnik().subscribe(
      (data)=>{
        this.ucesnici = data;
      }
    )
    
  }

  public compare(a:any, b:any){
    return a.id == b.id;
  }
  
  public add(){
    this.service.createRociste(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno dodato rociste sa ID: ${data.id}`, `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration: 1500});
    }
  }

  public update(){
    this.service.updateRociste(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno azurirano rociste sa ID: ${data.id}`, `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message)
      this.snackBar.open(`Neuspesno azuriranje`, `Zatvori`, {duration: 1500});
    }
  }

  public delete(){
    this.service.deleteRociste(this.data.id).subscribe(
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
