package com.revature.TeamCP2;

import com.revature.TeamCP2.models.*;
import com.revature.TeamCP2.utils.ConnectionManager;
import org.hibernate.HibernateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamCP2Application {

	public static void main(String[] args) {
		SpringApplication.run(TeamCP2Application.class, args);

		ConnectionManager.getConnection()
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Cart.class)
				.addAnnotatedClass(CartItem.class)
				.addAnnotatedClass(OnSale.class)
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Payment.class)
				.addAnnotatedClass(ProductCategory.class)
				.addAnnotatedClass(UserAddress.class)
				.initializeDatasource();
	}

}
