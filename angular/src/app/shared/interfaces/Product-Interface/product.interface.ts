import { category } from "./category.interface"
import { onSale } from "./onsale.interface"


export interface Product {

  id?: number,
  name: string,
  descr: string,
  price: number,
  onSale: onSale,
  category: category,
  isFeatured: boolean
  image?: Blob,
}