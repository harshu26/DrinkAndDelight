import { Component, OnInit } from '@angular/core';
import { RawMaterialStock } from '../model/rawmaterialstock';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-list-stock',
  templateUrl: './list-stock.component.html',
  styleUrls: ['./list-stock.component.css']
})
export class ListStockComponent implements OnInit {

  //route:ActivatedRoute;
  stocks: RawMaterialStock[]=[];

  constructor() {
   // this.route =route; 
    let stock1 = new  RawMaterialStock("S10","O10",10,100,1000,"w10",new Date("2020-5-16"));
    let stock2 = new  RawMaterialStock("S11","O11",11,100,1100,"w11",new Date("2020-6-18"));
    this.stocks.push(stock1);
    this.stocks.push(stock2);
  }

  ngOnInit(): void {
  }

  fetched:RawMaterialStock=null;
  findStockById(form:any){
    let details=form.value;
    let id = details.id;
    for(let stock of this.stocks){
      if(stock.orderId===id){
        this.fetched=stock;
      }
    }
  }

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
  
}
