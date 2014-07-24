/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.alex.ap.coffeeShop;

import com.google.common.collect.ImmutableList;
import name.alex.ap.coffeeShop.items.Item;

class CompletedOrder {
    private final ImmutableList<Item> items;

    public CompletedOrder(ImmutableList<Item> items) {
        this.items = items;
    }
    
    public ImmutableList<Item> getItems() {
        return items;
    }
}
