package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundExeption;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Product first = new Book(1, "Lord of the Ring", 500, "Tolkien");
    private Product second = new Book(2, "Harry Potter", 350, "Rowling");
    private Product third = new TShirt(3, "Black", 500, "Reebok");
    private Product forth = new TShirt(4, "White", 600, "UnderArmor");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
    }

    @Test
    public void deletingAnExistingItem() {
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{second, third, forth};
        assertArrayEquals(actual, expected);

    }

    @Test
    public void attemptToDeleteNonExistentElement() {
        assertThrows(NotFoundExeption.class, () -> repository.removeById(5));
    }
}
