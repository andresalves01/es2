package com.jcg.hibernate.crud.operations;

import java.util.List;

import org.apache.log4j.Logger;

public class AppMain {

	public final static Logger logger = Logger.getLogger(AppMain.class);

	public static void main(String[] args) {
		logger.info(".......Hibernate Crud Operations Example.......\n");

		logger.info("\n=======CREATE RECORDS=======\n");
		DbOperations.createRecord();

		logger.info("\n=======READ RECORDS=======\n");
		List<Medico> viewContatos = DbOperations.displayRecords();
		if (viewContatos != null & viewContatos.size() > 0) {
			for (Medico contatoObj : viewContatos) {
				logger.info(contatoObj.toString());
			}
		}

		logger.info("\n=======UPDATE RECORDS=======\n");
		int updateId = 1;
		DbOperations.updateRecord(updateId);
		logger.info("\n=======READ RECORDS AFTER UPDATION=======\n");
		List<Medico> updateContato = DbOperations.displayRecords();
		if (updateContato != null & updateContato.size() > 0) {
			for (Medico contatoObj : updateContato) {
				logger.info(contatoObj.toString());
			}
		}

		logger.info("\n=======DELETE RECORD=======\n");
		int deleteId = 5;
		DbOperations.deleteRecord(deleteId);
		logger.info("\n=======READ RECORDS AFTER DELETION=======\n");
		List<Medico> deleteContatoRecord = DbOperations.displayRecords();
		for (Medico contatoObj : deleteContatoRecord) {
			logger.info(contatoObj.toString());
		}

		System.exit(0);
	}
}