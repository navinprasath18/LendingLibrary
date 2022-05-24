package com.lendinglib.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendinglib.app.data.ItemData;
import com.lendinglib.app.entities.Item;
import com.lendinglib.app.repo.ItemRepo;
import com.lendinglib.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService
{
    @Autowired
    ItemRepo repo;

    @Override
    public Item createItem (ItemData itemData)
    {
        Item item = new ObjectMapper().convertValue(itemData,Item.class);
        item.setCount_available(item.getTotal_count());
        return repo.save(item);
    }

    @Override
    public List<Item> getAll ()
    {
        return repo.findAll();
    }

    @Override
    public Item getItemByItemId (String id)
    {
        Optional<Item> item =repo.findById(id);
        if(item.isPresent())
        {
            return item.get();
        }
        return null;
    }

    @Override
    public Item updateItem (Item item)
    {
        return repo.saveAndFlush(item);
    }
}
