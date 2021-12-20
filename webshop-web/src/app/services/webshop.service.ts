import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/products/product";
import {firstValueFrom, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import { ShoppingCartSummary } from '../model/shopping-cart/shopping-cart-summary';
import { ShoppingCart } from '../model/shopping-cart/shopping-cart';
import { ShoppingCartItem } from '../model/shopping-cart/shopping-cart-item';


@Injectable({
  providedIn: 'root'
})
export class WebshopService {

  constructor(
    private httpClient: HttpClient) {
  }

  public getProducts(): Observable<Product[]> {
    // TODO: Remove above. Above is also a good example for how frontend developers can get by
    // based on a API contract, before the implementation exists.
    return this.httpClient.get<Product[]>(environment.baseApiUrl + '/products');
  }

  public async getShoppingCart(): Promise<ShoppingCartSummary> {
    // TODO: Remove above.
    const carts: ShoppingCartSummary[] = await firstValueFrom(this.httpClient.get<ShoppingCartSummary[]>(`${environment.baseApiUrl}/shopping-carts`));
    // Get first, create a new one if that doesnt exist. Crude implementation..
    if (carts.length === 0) {
      return await firstValueFrom(this.httpClient.post<ShoppingCartSummary>(`${environment.baseApiUrl}/shopping-carts`, {productItems: []}));
    }
    return carts[0];
  }

  public addProductToCart(shoppingCart: ShoppingCartSummary, product: Product): Observable<ShoppingCartSummary> {
    const cartItem = shoppingCart.productItems.find(item => item.productId === product.id);
    if (cartItem) {
      cartItem.amount = cartItem.amount + 1;
    }
    else {
      shoppingCart.productItems.push({productId: product.id, amount: 1});
    }
    return this.httpClient.put<ShoppingCartSummary>(`${environment.baseApiUrl}/shopping-carts/${shoppingCart.id}`, shoppingCart);
  }
}
