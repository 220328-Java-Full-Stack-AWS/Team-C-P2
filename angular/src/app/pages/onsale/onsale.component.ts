import {Component, OnDestroy, OnInit} from "@angular/core";
import {IProducts} from "./IProduct.interface";
import {OnSaleService} from "./onsale.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'onSaleProducts',
  templateUrl: './onsale.component.html',
  styleUrls:['./onsale.component.scss']
})
export class OnSaleComponent implements OnInit, OnDestroy{
  onSalePageTitle: string = 'On SALE Products';
  imageWidth: number = 50;
  imageMargin: number = 2;
  showImage:boolean = false;
  errorMessage: string = '';
  sub!: Subscription;
  //listFilter: string ='cart';
//GETTERS AND SETTERS=====================================
  private _listFilter: string = '';//declare the variable to hold the value
  get listFilter(): string{
    return  this._listFilter;
  }
  set listFilter(value:string){
    this._listFilter = value;
    console.log('In setter',value);
    this.filteredOnsaleProducts = this.performFilter(value);
  }
  // ============================================================
  filteredOnsaleProducts: IProducts[] = [];

  onSaleproducts: IProducts[]=[]

  constructor(private onsaleProductService: OnSaleService) {
  }

  performFilter(filterBy: string): IProducts[]{ //filter list of products and if empty, it returns all products
    filterBy = filterBy.toLocaleLowerCase();
    return this.onSaleproducts.filter((osproduct: IProducts)=>
      osproduct.name.toLocaleLowerCase().includes(filterBy));
  }

  toggleImage(): void{
    this.showImage = !this.showImage;
  }
  ngOnInit(): void{

    this.sub = this.onsaleProductService.getOnSaleProducts().subscribe({
      next: onSaleProducts => {
        this.onSaleproducts = onSaleProducts;
        this.filteredOnsaleProducts = this.onSaleproducts;
      },
      error: err => this.errorMessage = err
    });

  }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
