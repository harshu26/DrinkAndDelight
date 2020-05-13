import { Component, OnInit } from '@angular/core';
import { Supplier } from '../model/supplier';

@Component({
  selector: 'app-list-supplier',
  templateUrl: './list-supplier.component.html',
  styleUrls: ['./list-supplier.component.css']
})
export class ListSupplierComponent implements OnInit {

  suppliers:Supplier[]=[];
  constructor() {
    let supplier1=new Supplier(111,"Harsh","Bhopal City",1234567890);
    let supplier2=new Supplier(112,"Rahul","Delhi-NCR ",18745972852);
    let supplier3=new Supplier(113,"Vijay","Indore City",141475585);
    this.suppliers.push(supplier1);
    this.suppliers.push(supplier2);
    this.suppliers.push(supplier3);
  }

  ngOnInit(): void {
  }

  fetchedSupplier:Supplier=null;
  fetchSupplierById(form:any){
    let details=form.value;
    let id = details.id;
    for(let supplier of this.suppliers){
      if(supplier.supplierId===id){
        this.fetchedSupplier=supplier;
      }
    }
  }

}
