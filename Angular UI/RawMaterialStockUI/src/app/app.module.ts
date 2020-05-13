import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddStockComponent } from './add-stock/add-stock.component';
import { AddSupplierComponent } from './add-supplier/add-supplier.component';
import { ListStockComponent } from './list-stock/list-stock.component';
import { ListSupplierComponent } from './list-supplier/list-supplier.component';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    AddStockComponent,
    AddSupplierComponent,
    ListStockComponent,
    ListSupplierComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
