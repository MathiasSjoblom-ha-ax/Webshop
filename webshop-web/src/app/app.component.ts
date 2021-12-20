import {Component, OnInit} from '@angular/core';
import { Product } from './model/products/product';
import { ShoppingCartSummary } from './model/shopping-cart/shopping-cart-summary';
import { WebshopService } from './services/webshop.service';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
];

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  shoppingCart: ShoppingCartSummary;

  products: Product[] = [];

  displayedColumns: string[] = ['name', 'total-price', 'amount'];
  what = ELEMENT_DATA;

  constructor(private webshopService: WebshopService) { }

  async ngOnInit() {
    this.webshopService.getProducts().subscribe(products => {
      this.products = products;
    })

    
    this.shoppingCart = await this.webshopService.getShoppingCart();
  }

  addProductToCart(product: Product) {
    this.webshopService.addProductToCart(this.shoppingCart, product).subscribe(updatedShoppingCart => {
      this.shoppingCart = updatedShoppingCart;
    })
  }

  findProduct(id: string): Product | undefined {
    return this.products.find(p => p.id === id);
  }
}
