package main;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RecordRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private RecordGenerator recordGenerator;
	
	public List<Record> selectAllItems() {
		return em.createQuery("select r from Record r", Record.class).getResultList();
	}
	
	public Record selectItem(String id) {
		Query query = em.createQuery("select r from Record r where r.id = :id");
		query.setParameter("id", id);
		return (Record) query.getSingleResult();
	}
	
	public String generateItem() throws IOException {
		Record r = new Record();
		r.setId(UUID.randomUUID().toString());
		r.setContent(recordGenerator.generateContent());
		r.setOrigin("Spring 5.3.21");
		r.setRecordTimestamp(recordGenerator.generateLocalDateTime());
		r.setSerial("sn" + (new Random().nextInt(8999999) + 1000001));
		
		em.persist(r);
		
		return "Record stored.";
	}

}
