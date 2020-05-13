export class Supplier{
    supplierId:number;
    supplierName:string;
    supplierAddress:string;
    supplierPhone:number;

    constructor(id:number,name:string,address:string,phone:number){
        this.supplierId=id;
        this.supplierName=name;
        this.supplierAddress=address;
        this.supplierPhone=phone;
    }
}