package pt.com.xml.codec;

import org.caudexorigo.io.UnsynchByteArrayInputStream;

import pt.com.types.SimpleFramingDecoder;
import pt.com.xml.SoapEnvelope;
import pt.com.xml.SoapSerializer;

public class SoapDecoder extends SimpleFramingDecoder
{
	public SoapDecoder(int max_message_size)
	{
		super(max_message_size);
	}

	@Override
	public Object processBody(byte[] packet)
	{
		UnsynchByteArrayInputStream bin = new UnsynchByteArrayInputStream(packet);
		SoapEnvelope msg = SoapSerializer.FromXml(bin);

		return Builder.soapToNetMessage(msg);
	}

}
