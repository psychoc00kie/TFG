package net.codejava.Repositories.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    public PurchaseRepository purchaseRepository;

    public List<Purchase> listAll() {
        return purchaseRepository.findAll();
    }

    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public Purchase get(Long id) {
        return purchaseRepository.findById(id).get();
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}
