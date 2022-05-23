import { Byte } from "@angular/compiler/src/util";

export interface Category {
    id?: number,
    name: string,
    description?: string,
    image: Byte[]
    
  }