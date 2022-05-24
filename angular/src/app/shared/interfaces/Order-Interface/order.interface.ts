import { Cart } from "../Cart-Interface/cart.interface";

export interface Order {
    cart?: Cart,
    dateCreated: String,
    id: number
}