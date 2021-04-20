package net.codejava.Box;

import net.codejava.Products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    @Autowired
    public BoxRepository box_repo;

    public List<Box> listAll(){
        return box_repo.findAll();
    }

    public void save(Box box) {
        box_repo.save(box);
    }

    public Box get(Long id) {
        return box_repo.findById(id).get();
    }

    public void delete(Long id) {
        box_repo.deleteById(id);
    }
}
