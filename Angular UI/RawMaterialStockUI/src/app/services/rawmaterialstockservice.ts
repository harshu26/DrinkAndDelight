import { HttpClient } from '@angular/common/http';
import { RawMaterialStock } from '../model/rawmaterialstock';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class RawMaterialStockService{
    client:HttpClient;
    constructor(client:HttpClient){
        this.client=client;
    }

    baseUrl = "http://localhost:8086/stocks";

    addStock(stock:RawMaterialStock): Observable<RawMaterialStock>{
        let url = this.baseUrl+"/add";
        let result :Observable<RawMaterialStock>=this.client.post<RawMaterialStock>(url,stock);
        return result;
    }

    fetchAllStocks():Observable<RawMaterialStock[]>{
        let url = this.baseUrl+"/getStocks";
        let result:Observable<RawMaterialStock[]>=this.client.get<RawMaterialStock[]>(url);
        return result;
    }

    findStockById(id:string):Observable<RawMaterialStock>{
        let url = this.baseUrl+'/get/'+id;
        let result:Observable<RawMaterialStock>=this.client.get<RawMaterialStock>(url);
        return result;
    }
}