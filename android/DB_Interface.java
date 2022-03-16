package com.example.a06_sqlite.placeholder;

import java.util.List;
import java.util.Map;

public interface DB_Interface {

    public int count();

    public int save(Character character);

    public int update(Character character);  // Not implemented

    public int deleteById(Long id);  // Not implemented

    public List<PlaceholderContent.PlaceholderItem> findAll();

    public Map<String, PlaceholderContent.PlaceholderItem> findAllDetails();

    public String getNameById(Long id);  // Not implemented

}
