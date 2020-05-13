import { Component, OnInit } from '@angular/core';
import { Supplier } from '../model/supplier';

@Component({
  selector: 'app-add-supplier',
  templateUrl: './add-supplier.component.html',
  styleUrls: ['./add-supplier.component.css']
})
export class AddSupplierComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  addedSupplier:Supplier=null;

  addSupplier(form:any){
    let details=form.value;
    let id=details.id;
    let name=details.name;
    let address=details.address;
    let phone=details.phone;
    let supplier=new Supplier(id,name,address,phone);
    this.addedSupplier=supplier;
    form.reset();
  }

}
