package org.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message = "Название не должно быть пустым...")
    @Size(min=2, max=100, message="Размер названия должен быть от 2 до 100 символов")
    private String title;
    @NotEmpty(message = "Строка автора не должна быть пустой...")
    @Size(min=2, max=100, message="Имя автора должно быть от 2 до 100 символов")
    private String author;
    @Min(value=1500, message = "Год написания книги должен быть более 1499")
    @Max(value=2024, message = "Год написания книги не может быть более 2024")
    private int year;

    public Book() {}

    public Book(String title, String author, int age) {
        this.title = title;
        this.author = author;
        this.year = age;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
