package com.gildedrose;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
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
        if (item.name.equals(AGED_BRIE)) {
            increaseQuality(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
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
            if (!item.name.equals(SULFURAS)) {
                decQuality(item);
            }
        }
    }

    private void updateExpiration(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn--;
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }


    private void processExpired(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            increaseQuality(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            item.quality = 0;
        } else if (item.name.equals(SULFURAS)) {
            return;
        } else {
            decQuality(item);
        }
    }

    private static void decQuality(Item item) {
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
