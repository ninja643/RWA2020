package rs.ac.ni.pmf.marko.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserEntity.class)
public class UserEntity_ {
	
	public static volatile SingularAttribute<UserEntity, String> username;
	public static volatile SingularAttribute<UserEntity, String> firstName;
	public static volatile SingularAttribute<UserEntity, String> lastName;
	
	public static volatile ListAttribute<UserEntity, TicketEntity> tickets;
}
