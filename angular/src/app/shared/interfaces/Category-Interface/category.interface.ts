import { Product } from "../Product-Interface/product.interface";

export interface Category {
  id?: number,
  productsAssociated?: Product[],
  name: string,
  description: string,
  image: Blob
}
