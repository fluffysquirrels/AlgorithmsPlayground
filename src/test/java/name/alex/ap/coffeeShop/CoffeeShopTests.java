package name.alex.ap.coffeeShop;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import name.alex.ap.coffeeShop.items.Coffee;
import name.alex.ap.coffeeShop.items.Item;
import name.alex.ap.coffeeShop.requests.*;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class CoffeeShopTests {
    @Test
    public void When_I_make_an_OrderRequest_of_one_CoffeeRequest_Then_CompletedOrder_has_one_Coffee_item()
            throws Exception{

        CompletedOrder completedOrder = makeOrder(
                ImmutableList.of((ItemRequest) new CoffeeRequest()));

        ImmutableList<Item> items = completedOrder.getItems();
        assertEquals(items.size(), 1, "completedOrder.items.size()");
        assertEquals(items.get(0).getClass(), Coffee.class, "completedOrder.get(0).class");
    }

    @Test
    public void When_I_make_an_empty_OrderRequest_The_CompletedOrder_has_no_items()
            throws Exception{
        ImmutableList<ItemRequest> itemRequests = ImmutableList.of();
        CompletedOrder completedOrder = makeOrder(itemRequests);

        ImmutableList<Item> items = completedOrder.getItems();
        assertEquals(items.size(), 0, "completedOrder.items.size()");
    }

    private CompletedOrder makeOrder(ImmutableList<ItemRequest> itemRequests) throws InterruptedException, ExecutionException, TimeoutException {
        CoffeeShop shop = getCoffeeShop();
        OrderRequest request = new OrderRequest(itemRequests);
        OrderProgress progress = shop.order(request);
        CompletedOrder completedOrder =
                progress.getCompletedOrderFuture().get(2, TimeUnit.SECONDS);
        return completedOrder;
    }

    private CoffeeShop getCoffeeShop() {
        return new CoffeeShop();
    }
}
