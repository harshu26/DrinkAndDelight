import { Component, OnInit } from '@angular/core';
import { RawMaterialStockService } from '../services/rawmaterialstockservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-update-stock',
  templateUrl: './update-stock.component.html',
  styleUrls: ['./update-stock.component.css']
})
export class UpdateStockComponent implements OnInit {
  service:RawMaterialStockService;
  constructor(service:RawMaterialStockService) {
    this.service=service;
   }

  ngOnInit(): void {
  }

  updateStatus=null;
  message:string="";
  updateStock(updateForm:any){
    let details=updateForm.value;
    let id=details.id;
    let date=details.date;

    let convertedDate = date.toString();

    let stock:Observable<string>=this.service.updateStock(id,convertedDate);
    stock.subscribe(
     result=>{ 
      this.message=result;
      this.updateStatus="success";
     },
     err=>{
       this.updateStatus="fail";
       console.log(err.error);
     }
    );
  }

}
