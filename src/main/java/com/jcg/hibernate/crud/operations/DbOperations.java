package com.jcg.hibernate.crud.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbOperations {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	public final static Logger logger = Logger.getLogger(DbOperations.class);

	// This Method Is Used To Create The Hibernate's SessionFactory Object
	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties())
				.build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	// Method 1: This Method Used To Create A New Student Record In The Database
	// Table
	public static void createRecord() {
		int count = 0;
		Medico medico = null;
		Especialidade[] especialidades = new Especialidade[5];
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			especialidades[0] = (Especialidade) sessionObj.save(new Especialidade("Pediatria"));
			especialidades[1] = (Especialidade) sessionObj.save(new Especialidade("Herbiatra"));
			especialidades[2] = (Especialidade) sessionObj.save(new Especialidade("Psiquiatria"));
			especialidades[3] = (Especialidade) sessionObj.save(new Especialidade("Pneumologia"));
			especialidades[4] = (Especialidade) sessionObj.save(new Especialidade("Medicina esportiva"));

			// Creating Transaction Entities
			for (int j = 0; j < 10; j++) {
				count = count + 1;
				medico = new Medico(j + "", "Aluno " + j, Math.random() * 100_000, especialidades[j % especialidades.length]);
				sessionObj.save(medico);
			}

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nSuccessfully Created '" + count + "' Records In The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 2: This Method Is Used To Display The Records From The Database Table
	@SuppressWarnings("unchecked")
	public static List<Medico> displayRecords() {
		List<Medico> medicoList = new ArrayList<Medico>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			medicoList = sessionObj.createQuery("FROM Medico" + Matricula.matricula).list();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return medicoList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table
	public static void updateRecord(String crm) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			Medico contatObj = (Medico) sessionObj.get(Medico.class, crm);
			contatObj.setNome("Jose");
			contatObj.setSalario(7500.25);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("Medico With Id?= " + crm + " Is Successfully Updated In The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(a): This Method Is Used To Delete A Particular Record From The
	// Database Table
	public static void deleteRecord(Integer id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Medico contatoObj = findRecordById(id);
			sessionObj.delete(contatoObj);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nContato With Id?= " + id + " Is Successfully Deleted From The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(b): This Method To Find Particular Record In The Database Table
	public static Medico findRecordById(Integer id) {
		Medico findContatoObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findContatoObj = (Medico) sessionObj.load(Medico.class, id);
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
		return findContatoObj;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM Medico" + Matricula.matricula);
			queryObj.executeUpdate();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}