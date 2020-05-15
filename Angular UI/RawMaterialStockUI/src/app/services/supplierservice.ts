import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Supplier } from '../model/supplier';
import { Observable } from 'rxjs';

@Injectable()
export class SupplierService{
    client:HttpClient;
    constructor(client:HttpClient){
        this.client=client;
    }
    
    baseUrl = "http://localhost:8086/stocks";

    addSupplier(supplier:Supplier):Observable<Supplier>{
        let url = this.baseUrl+"/addSupplier";
        let result :Observable<Supplier> = this.client.post<Supplier>(url,supplier);
        return result;
    }

    fetchSupplierById(id:number):Observable<Supplier>{
        let url = this.baseUrl+'/getS/'+id;
        let result:Observable<Supplier> = this.client.get<Supplier>(url);
        return result;
    }

    fetchAllSuppliers():Observable<Supplier[]>{
        let url = this.baseUrl+"/getSuppliers";
        let result:Observable<Supplier[]> = this.client.get<Supplier[]>(url);
        return result;
    }
}