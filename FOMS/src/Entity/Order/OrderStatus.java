package Entity.Order;

import java.io.Serializable;

public enum OrderStatus implements Serializable{
    ORDERPLACED,
	PROCESSING,
	READY,
	COLLECTED,
	DISCARDED
}
