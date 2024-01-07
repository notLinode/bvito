package ru.penzgtu.bvito.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.penzgtu.bvito.repository.ItemTagRepository;
import ru.penzgtu.bvito.repository.ItemRepository;
import ru.penzgtu.bvito.model.Item;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/items")
public class ItemsController {

    private final ItemRepository itemsRepo;
    private final ItemTagRepository tagRepo;

    public ItemsController(ItemRepository itemsRepo, ItemTagRepository tagRepo) {
        this.itemsRepo = itemsRepo;
        this.tagRepo = tagRepo;
    }

    @ModelAttribute
    public void addListingsToModel(Model model) {
        Item item1 = new Item();
        item1.setCreatedAt(new Date());
        item1.setName("Testing item1");
        item1.setDescription("No low-ballers. I know what I have.");
        item1.setPrice(BigDecimal.valueOf(25_000));

        Item item2 = new Item();
        item2.setCreatedAt(new Date());
        item2.setName("Testing item2");
        item2.setDescription("Default description");
        item2.setPrice(BigDecimal.valueOf(100));

        Iterable<Item> items = itemsRepo.findAll();

        model.addAttribute("items", items);
    }

    @GetMapping
    public String getAllItemsPage() {
        return "allItems";
    }

    @GetMapping("/{id}")
    public String getItemPage(@PathVariable String id, Model model) {
        Optional<Item> optionalItem = itemsRepo.findById(Long.valueOf(id));

        if (optionalItem.isPresent()) {
            model.addAttribute("item", optionalItem.get());
            return "item";
        }

        return "itemNotFound";
    }

    @GetMapping("/add")
    public String getAddItemForm(Model model) {
        model.addAttribute("addingItem", new Item());
        model.addAttribute("tags", tagRepo.findAll());
        return "addItem";
    }

    @PostMapping("/add")
    public String processItem(@Valid Item addingItem, Errors errors) {
        if (errors.hasErrors()) {
            return "addItem";
        }

        addingItem.setCreatedAt(new Date());
        Item savedItem = itemsRepo.save(addingItem);

        return "redirect:/items/" + savedItem.getId();
    }

}