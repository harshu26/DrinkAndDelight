import { Component, OnInit } from '@angular/core';
import { RawMaterialStock } from '../model/rawmaterialstock';
import { ActivatedRoute } from '@angular/router';
import { RawMaterialStockService } from '../services/rawmaterialstockservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-list-stock',
  templateUrl: './list-stock.component.html',
  styleUrls: ['./list-stock.component.css']
})
export class ListStockComponent implements OnInit {
  stockArray:RawMaterialStock[]=[];
  
  service:RawMaterialStockService;
  constructor(service:RawMaterialStockService) {
    this.service=service;
    let observable:Observable<RawMaterialStock[]>=this.service.fetchAllStocks();
    observable.subscribe(
      stocks=>{
        this.stockArray=stocks;
        console.log("length :"+this.stockArray.length);
      },
      err=>console.log(err)
    );
 }

  ngOnInit(): void {
  }

  
  foundStatus=null;
  fetched:RawMaterialStock=null;
  findStockById(form:any){
    let details=form.value;
    let id = details.id;
    let fetchedStock:Observable<RawMaterialStock>=this.service.findStockById(id);
    fetchedStock.subscribe(
      stock=>{
        this.fetched=stock;
        this.foundStatus="found";
      },
      err=>{
        this.foundStatus="notfound";
        console.log("error while fetching");
      }
    );
  }

/*
  updatedStock:RawMaterialStock=null;
  updateStock(updateForm:any){
    let details=updateForm.value;
    let id=details.id;
    let date=details.date;
    for(let i=0;i<this.stocks.length;i++){
      if(this.stocks[i].stockId===id){
        this.stocks[i].deliveryDate=date;
        this.updatedStock=this.stocks[i];
      }
    }
  }
  */
}
