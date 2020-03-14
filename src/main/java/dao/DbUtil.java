package dao;

import model.Model;

import java.util.List;


public class DbUtil {

    public List<Model> selectAllItem(Model model) {
        return model.selectAllItem();
    }

    public void deleteItem(Model model, Integer id) {
        model.deleteItem(id);
    }

    public void updateItem(Model model, Integer id) {
        model.updateItem(model, id);
    }

    public void insertItem(Model model) {
        model.insertItem(model);
    }

}
