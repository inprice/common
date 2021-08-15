package io.inprice.common.meta;

/**
 * @author mdpinar
 */
public enum QueueName {

	//link queues
	DEFAULT_LINKS_CAP1("default.links.queue.cap1", 1), //can be consumed only one instance
	DEFAULT_LINKS_CAP3("default.links.queue.cap3", 3),
	STATUS_CHANGING_LINKS("status.changing.links.queue"),

	//managerial queues
	SENDING_EMAILS("sending.emails.queue", 1),
	ACCESS_LOG("access.log.queue", 1)
	;

	private String name;
	private int capacity;

	private QueueName(String name) {
		this.name = name;
	}
	
	private QueueName(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}
	
	public int getCapacity() {
		return capacity;
	}

}
