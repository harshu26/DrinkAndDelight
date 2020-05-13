export class RawMaterialStock {
    stockId: string;
    orderId: string;
    price_per_unit: number;
    //quantity_Value: number;
    quantity_unit: number;
    price: number;
    warehouseId: string;
    deliveryDate: Date;
  //  manufacturingDate: Date;
   // expiryDate: Date;
   // qualityCheck: string;

   constructor(sId:string,oId:string,price_unit:number,unit:number,price:number,wId:string,delivery:Date){
       this.stockId=sId;
       this.orderId=oId;
       this.price_per_unit=price_unit;
       this.quantity_unit=unit;
       this.price=price;
       this.warehouseId=wId;
       this.deliveryDate=delivery;
   }
}