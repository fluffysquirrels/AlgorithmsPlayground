package name.alex.ap.coffeeShop;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import name.alex.ap.coffeeShop.items.*;
import name.alex.ap.coffeeShop.requests.ItemRequest;

class CoffeeShop {
    ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(0);
    
    OrderProgress order(final OrderRequest request) {
        final OrderProgress progress = new OrderProgress(request);
        executor.schedule(new Runnable() {

            @Override
            public void run() {
                final ImmutableList.Builder<Item> itemsBuilder =
                        new ImmutableList.Builder<>();
                
                for(ItemRequest itemRequest: request.getItems()){
                    itemsBuilder.add(new Coffee());
                }
                
                final CompletedOrder completedOrder = new CompletedOrder(
                        itemsBuilder.build());
                progress.setComplete(
                        completedOrder);
            }
        }, 100, TimeUnit.MILLISECONDS);

        return progress;
    }

}
