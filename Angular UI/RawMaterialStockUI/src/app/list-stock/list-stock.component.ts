import { Component, OnInit } from '@angular/core';
import { RawMaterialStock } from '../model/rawmaterialstock';

@Component({
  selector: 'app-list-stock',
  templateUrl: './list-stock.component.html',
  styleUrls: ['./list-stock.component.css']
})
export class ListStockComponent implements OnInit {

  stocks: RawMaterialStock[]=[];
  constructor() { 
    let stock1 = new  RawMaterialStock("S10","O10",10,100,1000,"w10",new Date(5-13-2020));
    let stock2 = new  RawMaterialStock("S11","O11",11,100,1100,"w11",new Date(6-13-2020));
    this.stocks.push(stock1);
    this.stocks.push(stock2);
  }

  ngOnInit(): void {
  }

}
