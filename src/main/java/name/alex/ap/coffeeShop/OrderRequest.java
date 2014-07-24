package name.alex.ap.coffeeShop;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import name.alex.ap.coffeeShop.requests.ItemRequest;

class OrderRequest {
    private final ImmutableList<ItemRequest> items;

    public ImmutableList<ItemRequest> getItems() {
        return items;
    }

    public OrderRequest(ImmutableList<ItemRequest> items) {
        this.items = items;
    }
}
