import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule} from '@angular/platform-browser/animations' 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import { SudComponent } from './components/main/sud/sud.component';
import { UcesnikComponent } from './components/main/ucesnik/ucesnik.component';
import { RocisteComponent } from './components/main/rociste/rociste.component';
import { PredmetComponent } from './components/main/predmet/predmet.component';
import { HomeComponent } from './components/utility/home/home.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    SudComponent,
    UcesnikComponent,
    RocisteComponent,
    PredmetComponent,
    HomeComponent,
    AboutComponent,
    AuthorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
