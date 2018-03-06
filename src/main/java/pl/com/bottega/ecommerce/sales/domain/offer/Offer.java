package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    private List<Product> availableItems = new ArrayList<Product>();

    private List<Product> unavailableItems = new ArrayList<Product>();

    public Offer(List<Product> availabeItems, List<Product> unavailableItems) {
        this.availableItems = availabeItems;
        this.unavailableItems = unavailableItems;
    }

    public List<Product> getAvailableItems() {
        return availableItems;
    }

    public List<Product> getUnavailableItems() {
        return unavailableItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (availableItems == null ? 0 : availableItems.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Offer other = (Offer) obj;
        if (availableItems == null) {
            if (other.availableItems != null) {
                return false;
            }
        } else if (!availableItems.equals(other.availableItems)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param seenOffer
     * @param delta
     *            acceptable difference in percent
     * @return
     */
    public boolean sameAs(Offer seenOffer, double delta) {
        if (availableItems.size() != seenOffer.availableItems.size()) {
            return false;
        }

        for (Product item : availableItems) {
            Product sameItem = seenOffer.findItem(item.getProductId());
            if (sameItem == null) {
                return false;
            }
            if (!sameItem.sameAs(item, delta)) {
                return false;
            }
        }

        return true;
    }

    private Product findItem(String productId) {
        for (Product item : availableItems) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

}
