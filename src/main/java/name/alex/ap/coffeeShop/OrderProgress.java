package name.alex.ap.coffeeShop;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.HashSet;

class OrderProgress {
    private final OrderRequest request;
    private final SettableFuture<CompletedOrder> completedFuture = SettableFuture.create();

    OrderProgress(OrderRequest request) {
        this.request = request;
    }

    public ListenableFuture<CompletedOrder> getCompletedOrderFuture() {
        return completedFuture;
    }

    void setComplete(CompletedOrder complete){
        completedFuture.set(complete);
    }

    void setError(Throwable ex){
        completedFuture.setException(ex);
    }
}
