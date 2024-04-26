package Entity.Order;

import java.io.Serializable;

/**
 * Enum representing the status of an order.
 * Possible statuses include ORDERPLACED, PROCESSING, READY, COLLECTED, and DISCARDED.
 */

public enum OrderStatus implements Serializable{
    ORDERPLACED,
	PROCESSING,
	READY,
	COLLECTED,
	DISCARDED
}
