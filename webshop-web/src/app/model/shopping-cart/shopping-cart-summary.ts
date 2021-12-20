import { CostSummary } from "./cost-summary";
import { ShoppingCart } from "./shopping-cart";

export interface ShoppingCartSummary extends ShoppingCart {
  id:string
  cost: CostSummary
}
