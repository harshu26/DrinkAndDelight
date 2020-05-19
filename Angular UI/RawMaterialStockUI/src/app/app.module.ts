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
import { StockMgtComponent } from './stock-mgt/stock-mgt.component';
import { RawMaterialStockService } from './services/rawmaterialstockservice';
import { SupplierService } from './services/supplierservice';
import { UpdateStockComponent } from './update-stock/update-stock.component';

@NgModule({
  declarations: [
    AppComponent,
    AddStockComponent,
    AddSupplierComponent,
    ListStockComponent,
    ListSupplierComponent,
    StockMgtComponent,
    UpdateStockComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [SupplierService, RawMaterialStockService],
  bootstrap: [AppComponent]
})
export class AppModule { }
