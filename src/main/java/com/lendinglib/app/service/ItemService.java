package com.lendinglib.app.service;

import com.lendinglib.app.data.ItemData;
import com.lendinglib.app.entities.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService
{

    Item createItem(ItemData data);

    List<Item> getAll();

    Item getItemByItemId(String id);

    Item updateItem(Item item);
}
