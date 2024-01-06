package ru.penzgtu.bvito.items;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

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

        List<Item> items = List.of(item1, item2);

        model.addAttribute("items", items);
    }

    @GetMapping
    public String getItemsPage() {
        return "items";
    }

}