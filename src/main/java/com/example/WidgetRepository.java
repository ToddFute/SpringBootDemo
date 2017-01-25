package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tbradfute on 1/24/17.
 */
public interface WidgetRepository extends CrudRepository<Widget, Long> {
    List<Widget> findByColor(String color);
}
