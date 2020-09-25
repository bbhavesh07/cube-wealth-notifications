package com.cubewealth.notifications.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.cubewealth.notifications.model.Event;

//ReadOnlyRepository
public interface EventRepository extends Repository<Event, Long> {

	  @Query("select e from Event e where e.userid = ?1 and e.noun = 'bill' and e.verb = 'pay'") 
	  List<Event> findTop1ByUseridAndBillPay(Long userId, Pageable page);
	  
	  @Query("select e from Event e where e.timestamp between ?2 and  ?3 and e.userid = ?1 and e.noun = 'bill' and e.verb = 'pay'") 
	  List<Event> findPaymentsWithin5MinByUserid(Long userId, Date t1, Date t2);
	  
	  @Query("select count(e) from Event e where e.timestamp >= ?2 and e.userid = ?1 and e.noun = 'fdbk' and e.verb = 'post'") 
	  long findFeedbackWithin15MinByUserid(Long userId, Date t1);

}

