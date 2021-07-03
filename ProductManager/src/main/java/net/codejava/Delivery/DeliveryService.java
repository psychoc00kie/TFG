package net.codejava.Delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
@Service
public class DeliveryService {




        @Autowired
        private EntityManager entityManager;

        @Autowired
        public DeliveryRepository deliveryRepository;

        public List<Delivery> listAll(){ return deliveryRepository.findAll();}

        public List<Delivery> listPending(){

            List<Delivery> aux = deliveryRepository.findAll();
            for(int i = 0 ; i<aux.size() ; i++){

                if(aux.get(i).getStatus() == 1)
                    aux.remove(aux.get(i));
            }
            return aux;
        }

        public List<Delivery> listCompleted(){

            List<Delivery> aux = deliveryRepository.findAll();
            for(int i = 0 ; i<aux.size() ; i++){

                if(aux.get(i).getStatus() == 0)
                    aux.remove(aux.get(i));
            }
            return aux;
        }

        public void updateStatus(int id, int statusCode){

            Delivery delivery = entityManager.find(Delivery.class, id);

            entityManager.getTransaction().begin();
            delivery.setStatus(statusCode);
            entityManager.getTransaction().commit();

        }

        public void save(Delivery delivery) { deliveryRepository.save(delivery);}

        public Delivery get(int id){return deliveryRepository.findById(id).get();}

        public void delete(int id){ deliveryRepository.deleteById(id);}


}
