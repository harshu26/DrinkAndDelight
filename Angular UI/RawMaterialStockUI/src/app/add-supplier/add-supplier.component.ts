import { Component, OnInit } from '@angular/core';
import { Supplier } from '../model/supplier';
import { SupplierService } from '../services/supplierservice';

@Component({
  selector: 'app-add-supplier',
  templateUrl: './add-supplier.component.html',
  styleUrls: ['./add-supplier.component.css']
})
export class AddSupplierComponent implements OnInit {
  service:SupplierService;
  constructor(service:SupplierService) {
    this.service=service;
   }

  ngOnInit(): void {
  }
  addedSupplier:Supplier=null;

  addSupplier(form:any){
    let details=form.value;
    let id=details.id;
    let name=details.name;
    let address=details.address;
    let phone=details.phone;
    this.addedSupplier = new Supplier();
    this.addedSupplier.supplierId=id;
    this.addedSupplier.supplierName=name;
    this.addedSupplier.supplierAddress=address;
    this.addedSupplier.supplierPhoneNo=phone;

    let result = this.service.addSupplier(this.addedSupplier);
    result.subscribe((supplier:Supplier)=>{
      this.addedSupplier=supplier;
    },
    err=>{
      console.log("error:"+err);
    }
    );
    form.reset();
  }

}
