/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package pt.com.broker.codec.thrift;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

class Notification implements org.apache.thrift.TBase<Notification, Notification._Fields>, java.io.Serializable, Cloneable
{
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Notification");

	private static final org.apache.thrift.protocol.TField DESTINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("destination", org.apache.thrift.protocol.TType.STRING, (short) 1);
	private static final org.apache.thrift.protocol.TField SUBSCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("subscription", org.apache.thrift.protocol.TType.STRING, (short) 2);
	private static final org.apache.thrift.protocol.TField DESTINATION_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("destination_type", org.apache.thrift.protocol.TType.I32, (short) 3);
	private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRUCT, (short) 4);

	public String destination;
	public String subscription;
	/**
	 * 
	 * @see DestinationType
	 */
	public DestinationType destination_type;
	public BrokerMessage message;

	/** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum
	{
		DESTINATION((short) 1, "destination"), SUBSCRIPTION((short) 2, "subscription"),
		/**
		 * 
		 * @see DestinationType
		 */
		DESTINATION_TYPE((short) 3, "destination_type"), MESSAGE((short) 4, "message");

		private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

		static
		{
			for (_Fields field : EnumSet.allOf(_Fields.class))
			{
				byName.put(field.getFieldName(), field);
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, or null if its not found.
		 */
		public static _Fields findByThriftId(int fieldId)
		{
			switch (fieldId)
			{
			case 1: // DESTINATION
				return DESTINATION;
			case 2: // SUBSCRIPTION
				return SUBSCRIPTION;
			case 3: // DESTINATION_TYPE
				return DESTINATION_TYPE;
			case 4: // MESSAGE
				return MESSAGE;
			default:
				return null;
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, throwing an exception if it is not found.
		 */
		public static _Fields findByThriftIdOrThrow(int fieldId)
		{
			_Fields fields = findByThriftId(fieldId);
			if (fields == null)
				throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
			return fields;
		}

		/**
		 * Find the _Fields constant that matches name, or null if its not found.
		 */
		public static _Fields findByName(String name)
		{
			return byName.get(name);
		}

		private final short _thriftId;
		private final String _fieldName;

		_Fields(short thriftId, String fieldName)
		{
			_thriftId = thriftId;
			_fieldName = fieldName;
		}

		public short getThriftFieldId()
		{
			return _thriftId;
		}

		public String getFieldName()
		{
			return _fieldName;
		}
	}

	// isset id assignments

	public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
	static
	{
		Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
		tmpMap.put(_Fields.DESTINATION, new org.apache.thrift.meta_data.FieldMetaData("destination", org.apache.thrift.TFieldRequirementType.DEFAULT, new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.SUBSCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("subscription", org.apache.thrift.TFieldRequirementType.DEFAULT, new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.DESTINATION_TYPE, new org.apache.thrift.meta_data.FieldMetaData("destination_type", org.apache.thrift.TFieldRequirementType.DEFAULT, new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, DestinationType.class)));
		tmpMap.put(_Fields.MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("message", org.apache.thrift.TFieldRequirementType.DEFAULT, new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, BrokerMessage.class)));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Notification.class, metaDataMap);
	}

	public Notification()
	{
	}

	public Notification(String destination, String subscription, DestinationType destination_type, BrokerMessage message)
	{
		this();
		this.destination = destination;
		this.subscription = subscription;
		this.destination_type = destination_type;
		this.message = message;
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public Notification(Notification other)
	{
		if (other.isSetDestination())
		{
			this.destination = other.destination;
		}
		if (other.isSetSubscription())
		{
			this.subscription = other.subscription;
		}
		if (other.isSetDestination_type())
		{
			this.destination_type = other.destination_type;
		}
		if (other.isSetMessage())
		{
			this.message = new BrokerMessage(other.message);
		}
	}

	public Notification deepCopy()
	{
		return new Notification(this);
	}

	@Override
	public void clear()
	{
		this.destination = null;
		this.subscription = null;
		this.destination_type = null;
		this.message = null;
	}

	public String getDestination()
	{
		return this.destination;
	}

	public Notification setDestination(String destination)
	{
		this.destination = destination;
		return this;
	}

	public void unsetDestination()
	{
		this.destination = null;
	}

	/** Returns true if field destination is set (has been assigned a value) and false otherwise */
	public boolean isSetDestination()
	{
		return this.destination != null;
	}

	public void setDestinationIsSet(boolean value)
	{
		if (!value)
		{
			this.destination = null;
		}
	}

	public String getSubscription()
	{
		return this.subscription;
	}

	public Notification setSubscription(String subscription)
	{
		this.subscription = subscription;
		return this;
	}

	public void unsetSubscription()
	{
		this.subscription = null;
	}

	/** Returns true if field subscription is set (has been assigned a value) and false otherwise */
	public boolean isSetSubscription()
	{
		return this.subscription != null;
	}

	public void setSubscriptionIsSet(boolean value)
	{
		if (!value)
		{
			this.subscription = null;
		}
	}

	/**
	 * 
	 * @see DestinationType
	 */
	public DestinationType getDestination_type()
	{
		return this.destination_type;
	}

	/**
	 * 
	 * @see DestinationType
	 */
	public Notification setDestination_type(DestinationType destination_type)
	{
		this.destination_type = destination_type;
		return this;
	}

	public void unsetDestination_type()
	{
		this.destination_type = null;
	}

	/** Returns true if field destination_type is set (has been assigned a value) and false otherwise */
	public boolean isSetDestination_type()
	{
		return this.destination_type != null;
	}

	public void setDestination_typeIsSet(boolean value)
	{
		if (!value)
		{
			this.destination_type = null;
		}
	}

	public BrokerMessage getMessage()
	{
		return this.message;
	}

	public Notification setMessage(BrokerMessage message)
	{
		this.message = message;
		return this;
	}

	public void unsetMessage()
	{
		this.message = null;
	}

	/** Returns true if field message is set (has been assigned a value) and false otherwise */
	public boolean isSetMessage()
	{
		return this.message != null;
	}

	public void setMessageIsSet(boolean value)
	{
		if (!value)
		{
			this.message = null;
		}
	}

	public void setFieldValue(_Fields field, Object value)
	{
		switch (field)
		{
		case DESTINATION:
			if (value == null)
			{
				unsetDestination();
			}
			else
			{
				setDestination((String) value);
			}
			break;

		case SUBSCRIPTION:
			if (value == null)
			{
				unsetSubscription();
			}
			else
			{
				setSubscription((String) value);
			}
			break;

		case DESTINATION_TYPE:
			if (value == null)
			{
				unsetDestination_type();
			}
			else
			{
				setDestination_type((DestinationType) value);
			}
			break;

		case MESSAGE:
			if (value == null)
			{
				unsetMessage();
			}
			else
			{
				setMessage((BrokerMessage) value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field)
	{
		switch (field)
		{
		case DESTINATION:
			return getDestination();

		case SUBSCRIPTION:
			return getSubscription();

		case DESTINATION_TYPE:
			return getDestination_type();

		case MESSAGE:
			return getMessage();

		}
		throw new IllegalStateException();
	}

	/** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
	public boolean isSet(_Fields field)
	{
		if (field == null)
		{
			throw new IllegalArgumentException();
		}

		switch (field)
		{
		case DESTINATION:
			return isSetDestination();
		case SUBSCRIPTION:
			return isSetSubscription();
		case DESTINATION_TYPE:
			return isSetDestination_type();
		case MESSAGE:
			return isSetMessage();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that)
	{
		if (that == null)
			return false;
		if (that instanceof Notification)
			return this.equals((Notification) that);
		return false;
	}

	public boolean equals(Notification that)
	{
		if (that == null)
			return false;

		boolean this_present_destination = true && this.isSetDestination();
		boolean that_present_destination = true && that.isSetDestination();
		if (this_present_destination || that_present_destination)
		{
			if (!(this_present_destination && that_present_destination))
				return false;
			if (!this.destination.equals(that.destination))
				return false;
		}

		boolean this_present_subscription = true && this.isSetSubscription();
		boolean that_present_subscription = true && that.isSetSubscription();
		if (this_present_subscription || that_present_subscription)
		{
			if (!(this_present_subscription && that_present_subscription))
				return false;
			if (!this.subscription.equals(that.subscription))
				return false;
		}

		boolean this_present_destination_type = true && this.isSetDestination_type();
		boolean that_present_destination_type = true && that.isSetDestination_type();
		if (this_present_destination_type || that_present_destination_type)
		{
			if (!(this_present_destination_type && that_present_destination_type))
				return false;
			if (!this.destination_type.equals(that.destination_type))
				return false;
		}

		boolean this_present_message = true && this.isSetMessage();
		boolean that_present_message = true && that.isSetMessage();
		if (this_present_message || that_present_message)
		{
			if (!(this_present_message && that_present_message))
				return false;
			if (!this.message.equals(that.message))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	public int compareTo(Notification other)
	{
		if (!getClass().equals(other.getClass()))
		{
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;
		Notification typedOther = (Notification) other;

		lastComparison = Boolean.valueOf(isSetDestination()).compareTo(typedOther.isSetDestination());
		if (lastComparison != 0)
		{
			return lastComparison;
		}
		if (isSetDestination())
		{
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.destination, typedOther.destination);
			if (lastComparison != 0)
			{
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetSubscription()).compareTo(typedOther.isSetSubscription());
		if (lastComparison != 0)
		{
			return lastComparison;
		}
		if (isSetSubscription())
		{
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subscription, typedOther.subscription);
			if (lastComparison != 0)
			{
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetDestination_type()).compareTo(typedOther.isSetDestination_type());
		if (lastComparison != 0)
		{
			return lastComparison;
		}
		if (isSetDestination_type())
		{
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.destination_type, typedOther.destination_type);
			if (lastComparison != 0)
			{
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetMessage()).compareTo(typedOther.isSetMessage());
		if (lastComparison != 0)
		{
			return lastComparison;
		}
		if (isSetMessage())
		{
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, typedOther.message);
			if (lastComparison != 0)
			{
				return lastComparison;
			}
		}
		return 0;
	}

	public _Fields fieldForId(int fieldId)
	{
		return _Fields.findByThriftId(fieldId);
	}

	public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException
	{
		org.apache.thrift.protocol.TField field;
		iprot.readStructBegin();
		while (true)
		{
			field = iprot.readFieldBegin();
			if (field.type == org.apache.thrift.protocol.TType.STOP)
			{
				break;
			}
			switch (field.id)
			{
			case 1: // DESTINATION
				if (field.type == org.apache.thrift.protocol.TType.STRING)
				{
					this.destination = iprot.readString();
				}
				else
				{
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
				}
				break;
			case 2: // SUBSCRIPTION
				if (field.type == org.apache.thrift.protocol.TType.STRING)
				{
					this.subscription = iprot.readString();
				}
				else
				{
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
				}
				break;
			case 3: // DESTINATION_TYPE
				if (field.type == org.apache.thrift.protocol.TType.I32)
				{
					this.destination_type = DestinationType.findByValue(iprot.readI32());
				}
				else
				{
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
				}
				break;
			case 4: // MESSAGE
				if (field.type == org.apache.thrift.protocol.TType.STRUCT)
				{
					this.message = new BrokerMessage();
					this.message.read(iprot);
				}
				else
				{
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
				}
				break;
			default:
				org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
			}
			iprot.readFieldEnd();
		}
		iprot.readStructEnd();

		// check for required fields of primitive type, which can't be checked in the validate method
		validate();
	}

	public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException
	{
		validate();

		oprot.writeStructBegin(STRUCT_DESC);
		if (this.destination != null)
		{
			oprot.writeFieldBegin(DESTINATION_FIELD_DESC);
			oprot.writeString(this.destination);
			oprot.writeFieldEnd();
		}
		if (this.subscription != null)
		{
			oprot.writeFieldBegin(SUBSCRIPTION_FIELD_DESC);
			oprot.writeString(this.subscription);
			oprot.writeFieldEnd();
		}
		if (this.destination_type != null)
		{
			oprot.writeFieldBegin(DESTINATION_TYPE_FIELD_DESC);
			oprot.writeI32(this.destination_type.getValue());
			oprot.writeFieldEnd();
		}
		if (this.message != null)
		{
			oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
			this.message.write(oprot);
			oprot.writeFieldEnd();
		}
		oprot.writeFieldStop();
		oprot.writeStructEnd();
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("Notification(");
		boolean first = true;

		sb.append("destination:");
		if (this.destination == null)
		{
			sb.append("null");
		}
		else
		{
			sb.append(this.destination);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("subscription:");
		if (this.subscription == null)
		{
			sb.append("null");
		}
		else
		{
			sb.append(this.subscription);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("destination_type:");
		if (this.destination_type == null)
		{
			sb.append("null");
		}
		else
		{
			sb.append(this.destination_type);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("message:");
		if (this.message == null)
		{
			sb.append("null");
		}
		else
		{
			sb.append(this.message);
		}
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException
	{
		// check for required fields
	}

	private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException
	{
		try
		{
			write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
		}
		catch (org.apache.thrift.TException te)
		{
			throw new java.io.IOException(te);
		}
	}

	private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException
	{
		try
		{
			read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
		}
		catch (org.apache.thrift.TException te)
		{
			throw new java.io.IOException(te);
		}
	}

}
