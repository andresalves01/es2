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
		List<Medico> viewDoctors = DbOperations.displayRecords();
		if (viewDoctors != null & viewDoctors.size() > 0) {
			for (Medico medicoObj : viewDoctors) {
				logger.info(medicoObj.toString());
			}
		}

		logger.info("\n=======UPDATE RECORDS=======\n");
		String updateId = "5";
		DbOperations.updateRecord(updateId);
		logger.info("\n=======READ RECORDS AFTER UPDATION=======\n");
		List<Medico> updateDoctor = DbOperations.displayRecords();
		if (updateDoctor != null & updateDoctor.size() > 0) {
			for (Medico doctor : updateDoctor) {
				logger.info(doctor.toString());
			}
		}

		logger.info("\n=======DELETE RECORD=======\n");
		String deleteId = "4";
		DbOperations.deleteRecord(deleteId);
		logger.info("\n=======READ RECORDS AFTER DELETION=======\n");
		List<Medico> deleteDoctorRecord = DbOperations.displayRecords();
		for (Medico medicoObj : deleteDoctorRecord) {
			logger.info(medicoObj.toString());
		}

		System.exit(0);
	}
}