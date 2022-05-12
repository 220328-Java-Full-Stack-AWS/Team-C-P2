package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.Payment;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class PaymentRepository extends AbstractHibernateRepo<Payment> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public PaymentRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    //@Override
    public Payment create(Payment payment) {
        Transaction transaction = session.beginTransaction();
        session.save(payment);
        transaction.commit();
        return payment;
    }


    @Override
    public Optional<Payment> getById(int id) {

        TypedQuery<Payment> query = session.createQuery("from Payment where id = :id");
        query.setParameter("id", id);

        Payment payment = query.getSingleResult();

        return Optional.ofNullable(payment);
    }

    @Override
    public List<Payment> getAll() {
        Query query = session.createQuery("from Payment ");

        List<Payment> results = query.list();

        List<Payment> paymentList = new LinkedList<>();

        for (Payment result : results) {
            Payment payment = new Payment();
            payment.setId(result.getId());
            payment.setNetwork(result.getNetwork());
            payment.setIssuer(result.getIssuer());
            payment.setCardNumber(result.getCardNumber());
            payment.setSecurityCode(result.getSecurityCode());
            payment.setExpirationDate(result.getExpirationDate());

            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public void deleteById(int id) {
        Optional<Payment> payment = this.getById(id);
        session.delete(payment);
    }

    @Override
    public Payment update(Payment payment) {
        Transaction transaction = session.beginTransaction();
        Optional<Payment> updatePayment = (Optional<Payment>)
                session.get(String.valueOf(Payment.class), payment.getId());

        updatePayment.get().setUser(payment.getUser());
        updatePayment.get().setIssuer(payment.getIssuer());
        updatePayment.get().setNetwork(payment.getNetwork());
        updatePayment.get().setCardNumber(payment.getCardNumber());
        updatePayment.get().setSecurityCode(payment.getSecurityCode());
        updatePayment.get().setExpirationDate(payment.getExpirationDate());

        transaction.commit();


        return payment;
    }

    @Override
    public void delete(Payment model) throws ItemHasNoIdException {
        if(model.getId() == null)
            throw new ItemHasNoIdException();

        deleteById(model.getId().intValue());
    }

    @Override
    public void start() {
        this.session = connectionManager.getSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}


