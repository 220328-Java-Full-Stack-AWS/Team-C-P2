package com.revature.TeamCP2;

import com.revature.TeamCP2.models.Product;
import com.revature.TeamCP2.models.User;
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
				.initializeDatasource();
	}

}
