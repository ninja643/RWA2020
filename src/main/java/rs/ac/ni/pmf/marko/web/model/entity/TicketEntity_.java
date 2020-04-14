package rs.ac.ni.pmf.marko.web.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TicketEntity.class)
public class TicketEntity_ {
	public static volatile SingularAttribute<TicketEntity, Integer> id;
	
	public static volatile SingularAttribute<TicketEntity, UserEntity> user;
	
	public static volatile SingularAttribute<TicketEntity, String> title;
	public static volatile SingularAttribute<TicketEntity, String> description;
}
