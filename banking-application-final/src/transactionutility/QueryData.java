package transactionutility;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controller.FactorySession;
import pojo.TransferInform;



public class QueryData {
  static Session session=null;
  
	public static int balanceQuery(int currentbalance,int useraccount) {
		 
		session=FactorySession.configUtil();
		int id=0;

		try {
			Transaction transaction = session.beginTransaction();
			String sqlquery = "update Registration set balance=:balance where account=:useraccount";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sqlquery);
			query.setParameter("balance",currentbalance);
			query.setParameter("useraccount",useraccount);
			id = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {

		e.printStackTrace();
		}
		
		return id;
	}
	
	public static void transcationQuery(int ammount,int Benificaryaccountid,int useraccountid,long transcationid ) {
		
		try {
			Transaction transaction = session.beginTransaction();
			TransferInform transferInform = new TransferInform();
			transferInform.setBeneficiaryaccountid(Benificaryaccountid);
			transferInform.setUseraccountid(useraccountid);
			transferInform.setAmmount(ammount);
			transferInform.setTranscationID(transcationid);
			
			 // transferInform.setDateformate( ft.format(date));
			  
			session.save(transferInform);
			transaction.commit();
		} catch (Exception e) {

		e.printStackTrace();
		}
	}
}
