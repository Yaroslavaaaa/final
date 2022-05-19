package com.example.demo.service;

import com.example.demo.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAllItems();
    Item addItem(Item item);
    Item getItem(Long id);
    void deleteItem(long id);
    Item saveItem(Item item);
    Item findOneById(Long id);

}
