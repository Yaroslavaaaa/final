package com.example.demo.service.impl;


import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findOneById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
