package com.nt.test;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTestMethod {

	public static void main(String[] args) {
		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Product prod=null;
		Transaction tx=null;
		boolean flag=false;
		//Activate hibernate framework (BootStrap hibernate)
		cfg=new Configuration();
		//supply hibernate cfg file as input file
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		//build session factory
		factory=cfg.buildSessionFactory();
		//open session
		ses=factory.openSession();
		//create entity object to save with Db s/w
		prod=new Product();
		prod.setPid(426);prod.setPname("TV");prod.setPrice(102302);prod.setQty(1);
		try {
			tx=ses.beginTransaction();//internally calls con.setAutoCommit(false) to begin the test
			System.out.println("Data is saved");
			//save object
			ses.save(prod);
			flag=true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag=false;
			System.out.println("Data is not saved");
		}
		finally {
			//commit or rollback tx
			if(flag==true)
				tx.commit();//internally calls con.commit()
			else
				tx.rollback();//internally calls con.rollback
		ses.close();//close session object
		factory.close();//close session factory
		}//finally
	}//main
}//class

