import { Product } from "../Product-Interface/product.interface";

export interface CartItem {
    id?: number,
    cartId: number,
    product?: Product,
    quantity?: number,
    netPrice?: number,
  }