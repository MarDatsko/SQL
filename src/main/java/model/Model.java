package model;

import java.util.List;

public interface Model {
    List<Model> selectAllItem();

    void deleteItem(long id);

    void updateItem(Model model, long id);

    void insertItem(Model model);
}
