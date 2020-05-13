export class Supplier{
    supplierId:number;
    supplireName:string;
    supplierAddress:string;
    supplierPhone:number;

    constructor(id:number,name:string,address:string,phone:number){
        this.supplierId=id;
        this.supplireName=name;
        this.supplierAddress=address;
        this.supplierPhone=phone;
    }
}