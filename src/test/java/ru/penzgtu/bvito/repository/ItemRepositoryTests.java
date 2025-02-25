package ru.penzgtu.bvito.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.penzgtu.bvito.model.Customer;
import ru.penzgtu.bvito.model.Item;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ItemRepositoryTests {

    @Autowired
    private ItemRepository repo;

    @Test
    public void ItemRepository_GetAllByListedBy_Id_ReturnItems() {
        Customer customer = new Customer();
        customer.setId(1L);

        Item item = Item.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .listedBy(customer)
                .build();

        repo.save(item);
//        repo.getAllByListedBy_Id(1L, );

        Assertions.assertThat(true).isTrue();  // TODO
    }

    @Test
    public void ItemRepository_SaveAll_ReturnMoreThanOne() {
        Item item1 = Item.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();
        Item item2 = Item.builder()
                .createdAt(new Date())
                .name("Test listing #2")
                .description("")
                .price(BigDecimal.valueOf(2000))
                .build();

        List<Item> savedItem = repo.saveAll(List.of(item1, item2));

        Assertions.assertThat(savedItem).isNotNull();
        Assertions.assertThat(savedItem).hasSize(2);
    }

    @Test
    public void ItemRepository_FindById_ReturnSavedItem() {
        Item item = Item.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();

        repo.save(item);

        Optional<Item> returnedItem = repo.findById(item.getId());

        Assertions.assertThat(returnedItem).isPresent();
        Assertions.assertThat(returnedItem.get()).isEqualTo(item);
    }

    @Test
    public void ItemRepository_UpdateItem_ReturnItem() {
        Item item = Item.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();

        Item savedItem = repo.save(item);
        savedItem.setName("Updated listing");
        savedItem.setPrice(BigDecimal.valueOf(7800));
        Item updatedItem = repo.save(savedItem);

        Assertions.assertThat(updatedItem).isNotNull();
        Assertions.assertThat(updatedItem).isEqualTo(savedItem);
    }

}