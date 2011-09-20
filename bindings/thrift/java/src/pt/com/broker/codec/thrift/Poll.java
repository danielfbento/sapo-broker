/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package pt.com.broker.codec.thrift;

import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

class Poll implements org.apache.thrift.TBase<Poll, Poll._Fields>, java.io.Serializable, Cloneable
{
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Poll");

	private static final org.apache.thrift.protocol.TField ACTION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("action_id", org.apache.thrift.protocol.TType.STRING, (short) 1);
	private static final org.apache.thrift.protocol.TField DESTINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("destination", org.apache.thrift.protocol.TType.STRING, (short) 2);
	private static final org.apache.thrift.protocol.TField TIMEOUT_FIELD_DESC = new org.apache.thrift.protocol.TField("timeout", org.apache.thrift.protocol.TType.I64, (short) 3);

	public String action_id;
	public String destination;
	public long timeout;

	/** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum
	{
		ACTION_ID((short) 1, "action_id"), DESTINATION((short) 2, "destination"), TIMEOUT((short) 3, "timeout");

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
			case 1: // ACTION_ID
				return ACTION_ID;
			case 2: // DESTINATION
				return DESTINATION;
			case 3: // TIMEOUT
				return TIMEOUT;
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
	private static final int __TIMEOUT_ISSET_ID = 0;
	private BitSet __isset_bit_vector = new BitSet(1);

	public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
	static
	{
		Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
		tmpMap.put(_Fields.ACTION_ID, new org.apache.thrift.meta_data.FieldMetaData("action_id", org.apache.thrift.TFieldRequirementType.OPTIONAL, new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.DESTINATION, new org.apache.thrift.meta_data.FieldMetaData("destination", org.apache.thrift.TFieldRequirementType.DEFAULT, new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.TIMEOUT, new org.apache.thrift.meta_data.FieldMetaData("timeout", org.apache.thrift.TFieldRequirementType.DEFAULT, new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Poll.class, metaDataMap);
	}

	public Poll()
	{
	}

	public Poll(String destination, long timeout)
	{
		this();
		this.destination = destination;
		this.timeout = timeout;
		setTimeoutIsSet(true);
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public Poll(Poll other)
	{
		__isset_bit_vector.clear();
		__isset_bit_vector.or(other.__isset_bit_vector);
		if (other.isSetAction_id())
		{
			this.action_id = other.action_id;
		}
		if (other.isSetDestination())
		{
			this.destination = other.destination;
		}
		this.timeout = other.timeout;
	}

	public Poll deepCopy()
	{
		return new Poll(this);
	}

	@Override
	public void clear()
	{
		this.action_id = null;
		this.destination = null;
		setTimeoutIsSet(false);
		this.timeout = 0;
	}

	public String getAction_id()
	{
		return this.action_id;
	}

	public Poll setAction_id(String action_id)
	{
		this.action_id = action_id;
		return this;
	}

	public void unsetAction_id()
	{
		this.action_id = null;
	}

	/** Returns true if field action_id is set (has been assigned a value) and false otherwise */
	public boolean isSetAction_id()
	{
		return this.action_id != null;
	}

	public void setAction_idIsSet(boolean value)
	{
		if (!value)
		{
			this.action_id = null;
		}
	}

	public String getDestination()
	{
		return this.destination;
	}

	public Poll setDestination(String destination)
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

	public long getTimeout()
	{
		return this.timeout;
	}

	public Poll setTimeout(long timeout)
	{
		this.timeout = timeout;
		setTimeoutIsSet(true);
		return this;
	}

	public void unsetTimeout()
	{
		__isset_bit_vector.clear(__TIMEOUT_ISSET_ID);
	}

	/** Returns true if field timeout is set (has been assigned a value) and false otherwise */
	public boolean isSetTimeout()
	{
		return __isset_bit_vector.get(__TIMEOUT_ISSET_ID);
	}

	public void setTimeoutIsSet(boolean value)
	{
		__isset_bit_vector.set(__TIMEOUT_ISSET_ID, value);
	}

	public void setFieldValue(_Fields field, Object value)
	{
		switch (field)
		{
		case ACTION_ID:
			if (value == null)
			{
				unsetAction_id();
			}
			else
			{
				setAction_id((String) value);
			}
			break;

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

		case TIMEOUT:
			if (value == null)
			{
				unsetTimeout();
			}
			else
			{
				setTimeout((Long) value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field)
	{
		switch (field)
		{
		case ACTION_ID:
			return getAction_id();

		case DESTINATION:
			return getDestination();

		case TIMEOUT:
			return new Long(getTimeout());

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
		case ACTION_ID:
			return isSetAction_id();
		case DESTINATION:
			return isSetDestination();
		case TIMEOUT:
			return isSetTimeout();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that)
	{
		if (that == null)
			return false;
		if (that instanceof Poll)
			return this.equals((Poll) that);
		return false;
	}

	public boolean equals(Poll that)
	{
		if (that == null)
			return false;

		boolean this_present_action_id = true && this.isSetAction_id();
		boolean that_present_action_id = true && that.isSetAction_id();
		if (this_present_action_id || that_present_action_id)
		{
			if (!(this_present_action_id && that_present_action_id))
				return false;
			if (!this.action_id.equals(that.action_id))
				return false;
		}

		boolean this_present_destination = true && this.isSetDestination();
		boolean that_present_destination = true && that.isSetDestination();
		if (this_present_destination || that_present_destination)
		{
			if (!(this_present_destination && that_present_destination))
				return false;
			if (!this.destination.equals(that.destination))
				return false;
		}

		boolean this_present_timeout = true;
		boolean that_present_timeout = true;
		if (this_present_timeout || that_present_timeout)
		{
			if (!(this_present_timeout && that_present_timeout))
				return false;
			if (this.timeout != that.timeout)
				return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	public int compareTo(Poll other)
	{
		if (!getClass().equals(other.getClass()))
		{
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;
		Poll typedOther = (Poll) other;

		lastComparison = Boolean.valueOf(isSetAction_id()).compareTo(typedOther.isSetAction_id());
		if (lastComparison != 0)
		{
			return lastComparison;
		}
		if (isSetAction_id())
		{
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.action_id, typedOther.action_id);
			if (lastComparison != 0)
			{
				return lastComparison;
			}
		}
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
		lastComparison = Boolean.valueOf(isSetTimeout()).compareTo(typedOther.isSetTimeout());
		if (lastComparison != 0)
		{
			return lastComparison;
		}
		if (isSetTimeout())
		{
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timeout, typedOther.timeout);
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
			case 1: // ACTION_ID
				if (field.type == org.apache.thrift.protocol.TType.STRING)
				{
					this.action_id = iprot.readString();
				}
				else
				{
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
				}
				break;
			case 2: // DESTINATION
				if (field.type == org.apache.thrift.protocol.TType.STRING)
				{
					this.destination = iprot.readString();
				}
				else
				{
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
				}
				break;
			case 3: // TIMEOUT
				if (field.type == org.apache.thrift.protocol.TType.I64)
				{
					this.timeout = iprot.readI64();
					setTimeoutIsSet(true);
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
		if (this.action_id != null)
		{
			if (isSetAction_id())
			{
				oprot.writeFieldBegin(ACTION_ID_FIELD_DESC);
				oprot.writeString(this.action_id);
				oprot.writeFieldEnd();
			}
		}
		if (this.destination != null)
		{
			oprot.writeFieldBegin(DESTINATION_FIELD_DESC);
			oprot.writeString(this.destination);
			oprot.writeFieldEnd();
		}
		oprot.writeFieldBegin(TIMEOUT_FIELD_DESC);
		oprot.writeI64(this.timeout);
		oprot.writeFieldEnd();
		oprot.writeFieldStop();
		oprot.writeStructEnd();
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("Poll(");
		boolean first = true;

		if (isSetAction_id())
		{
			sb.append("action_id:");
			if (this.action_id == null)
			{
				sb.append("null");
			}
			else
			{
				sb.append(this.action_id);
			}
			first = false;
		}
		if (!first)
			sb.append(", ");
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
		sb.append("timeout:");
		sb.append(this.timeout);
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
			// it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
			__isset_bit_vector = new BitSet(1);
			read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
		}
		catch (org.apache.thrift.TException te)
		{
			throw new java.io.IOException(te);
		}
	}

}
