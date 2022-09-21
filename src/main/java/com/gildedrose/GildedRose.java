package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item item : items) { // for each for besser Lesbarkeit, ich habe indexen gelöscht
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        updateInventory(item);
        updateExpiration(item);
        if (isExpired(item)) {
            processExpired(item);
        }
    }

    private void updateInventory(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.sellIn < 11) {
                    increaseQuality(item);
                }

                if (item.sellIn < 6) {
                    increaseQuality(item);
                }
            }
        } else {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                decreaseQuality(item);
            }
        }
    }

    private void updateExpiration(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }


    private void processExpired(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
            return;
        } else {
            decreaseQuality(item);
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }


    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

        }
    }
}
