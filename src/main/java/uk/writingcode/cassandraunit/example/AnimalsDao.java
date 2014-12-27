package uk.writingcode.cassandraunit.example;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class AnimalsDao {

    private final Session session;

    public AnimalsDao(Session session) {
        this.session = session;
    }

    public List<Animal> find() {
        ResultSet resultSet = session.execute("SELECT name, type FROM animals");

        List<Animal> foundAnimals = new ArrayList<>();
        for (Row row : resultSet) {
            Animal foundAnimal = new Animal(row.getString("name"), row.getString("type"));
            foundAnimals.add(foundAnimal);
        }

        return foundAnimals;
    }

    public void insert(Animal animal) {
        session.execute("INSERT INTO animals (name, type) VALUES (?, ?)", animal.getName(), animal.getType());
    }
}
