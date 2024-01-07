package ru.penzgtu.bvito.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.penzgtu.bvito.model.ItemTag;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ItemTagRepositoryTests {

    @Autowired ItemTagRepository repo;

    @Test
    public void ItemTagRepository_FindById_ReturnTag() {
        ItemTag tag = new ItemTag("Tag");

        repo.save(tag);
        Optional<ItemTag> optionalTag = repo.findById("Tag");

        Assertions.assertThat(optionalTag).isPresent();
        Assertions.assertThat(optionalTag.get().getName()).isEqualTo("Tag");
    }

}