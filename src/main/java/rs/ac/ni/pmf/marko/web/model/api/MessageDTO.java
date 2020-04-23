package rs.ac.ni.pmf.marko.web.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "Data object for a single message")
public class MessageDTO {
	@ApiModelProperty(value = "The unique id of a message object")
	Integer id;
	
	@ApiModelProperty(value = "Username of a user that created a message", example = "markom")
	String username;
	
	@ApiModelProperty(value = "Id of a ticket that this message is related to")
	int ticketId;
	
	@ApiModelProperty(required = false, value = "Optional id of a message we're responding to")
	Integer replyToId;
	
	String title;
	String content;
}
