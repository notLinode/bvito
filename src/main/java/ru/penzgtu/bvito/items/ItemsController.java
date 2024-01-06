package ru.penzgtu.bvito.items;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/items")
public class ItemsController {

    private final ItemsRepository itemsRepo;
    private final ItemTagRepository tagRepo;

    public ItemsController(ItemsRepository itemsRepo, ItemTagRepository tagRepo) {
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

    @ModelAttribute(name = "tags")
    public Iterable<ItemTag> addTagsToModel() {
        return tagRepo.findAll();
    }

    @ModelAttribute(name = "addingItem")
    public Item addItemToModel() {
        return new Item();
    }

    @GetMapping
    public String getItemsPage() {
        return "items";
    }

    @GetMapping("/add")
    public String getAddItemForm() {
        return "addItem";
    }

    @PostMapping("/add")
    public String processItem(@Valid Item addingItem, BindingResult result) {
        if (result.hasErrors()) {
            return "addItem";
        }

        addingItem.setCreatedAt(new Date());
        itemsRepo.save(addingItem);

        return "redirect:/";
    }

}