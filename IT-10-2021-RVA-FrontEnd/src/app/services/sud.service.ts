import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class SudService {

  constructor(private httpClient:HttpClient) { }

  public getAllSud():Observable<any>{
    return this.httpClient.get('http://localhost:8080/sud')
  }

  

  
}
