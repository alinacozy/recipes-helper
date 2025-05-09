package com.example.recipes_helper.model;

public enum Rating {
    NONE("Нет оценки"),
    ONE("Очень плохо"),
    TWO("Плохо"),
    THREE("Средне"),
    FOUR("Хорошо"),
    FIVE("Отлично");

    private final String description;

    Rating(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}