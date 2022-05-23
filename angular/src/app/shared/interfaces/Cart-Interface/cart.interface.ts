import { CartItem } from "./cart-item.interface";

export interface Cart {
    cartItem?: CartItem,
    netPrice?: number
  }