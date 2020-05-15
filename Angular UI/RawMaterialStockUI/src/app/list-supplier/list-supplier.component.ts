import { Component, OnInit } from '@angular/core';
import { Supplier } from '../model/supplier';
import { SupplierService } from '../services/supplierservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-list-supplier',
  templateUrl: './list-supplier.component.html',
  styleUrls: ['./list-supplier.component.css']
})
export class ListSupplierComponent implements OnInit {
  supplierArray:Supplier[]=[];
  service:SupplierService;
  
  constructor(service:SupplierService) {
    this.service=service;
    let observable:Observable<Supplier[]> = this.service.fetchAllSuppliers();
    observable.subscribe(
      suppliers=>{
        this.supplierArray=suppliers;
        console.log("length :"+this.supplierArray.length);
      },
      err=>console.log(err)
    );
  }

  ngOnInit(): void {
  }

  foundStatus=null;
  fetchedSupplier:Supplier=null;
  fetchSupplierById(form:any){
    let details=form.value;
    let id = details.id;
    let fetched:Observable<Supplier> = this.service.fetchSupplierById(id);
    fetched.subscribe(
      supplier=>{
        this.fetchedSupplier=supplier;
        this.foundStatus="found";
      },
      err=>{
        this.foundStatus="notfound";
        console.log("Unable to fetch supplier");
      }
    );
  }

}
