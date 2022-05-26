import { CartOrder } from "../Cart-Interface/cart-order.interface"

export interface Order {
    cart?: CartOrder,
    dateCreated: String,
    id: number
}