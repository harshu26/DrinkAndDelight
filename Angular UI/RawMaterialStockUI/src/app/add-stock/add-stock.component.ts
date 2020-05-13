import { Component, OnInit } from '@angular/core';
import { RawMaterialStock } from '../model/rawmaterialstock';

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  addedStock: RawMaterialStock=null;

  addStock(stockForm:any){
    let details = stockForm.value;
    let sid = details.sid;
    let oid = details.oid;
    let price_unit = details.price_unit;
    let quantity = details.quantity;
    let price = details.price;
    let wid = details.wid;
    let delivery = details.delivery;
    let stock = new RawMaterialStock(sid,oid,price_unit,quantity,price,wid,delivery);
    this.addedStock=stock;
    stockForm.reset();
  }

}
