/*
 * Copyright 2012-2015, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aesop.client.sample;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.trpr.platform.core.impl.logging.LogFactory;
import org.trpr.platform.core.spi.logging.Logger;

import com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer;
import com.linkedin.databus.client.pub.ConsumerCallbackResult;
import com.linkedin.databus.client.pub.DbusEventDecoder;
import com.linkedin.databus.core.DbusEvent;

/**
 * <code>PersonEventConsumer</code> is a sub-type of {@link AbstractDatabusCombinedConsumer}} that simply prints the attributes of the sample
 * Person change event type.
 *
 * @author Regunath B
 * @version 1.0, 24 Jan 2014
 */
public class PersonEventConsumer extends AbstractDatabusCombinedConsumer {	
	
	/** Logger for this class*/
	public static final Logger LOGGER = LogFactory.getLogger(PersonEventConsumer.class);
	
	/** The frequency of logging consumed messages*/
	private static final long FREQUENCY_OF_LOGGING = 100;
	
	/** The event count*/
	private long eventCount = 0;
	
	/**
	 * Overriden superclass method. Returns the result of calling {@link PersonEventConsumer#processEvent(DbusEvent, DbusEventDecoder)}
	 * @see com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer#onDataEvent(com.linkedin.databus.core.DbusEvent, com.linkedin.databus.client.pub.DbusEventDecoder)
	 */
	public ConsumerCallbackResult onDataEvent(DbusEvent event,DbusEventDecoder eventDecoder) {
		return processEvent(event, eventDecoder);
	}

	/**
	 * Overriden superclass method. Returns the result of calling {@link PersonEventConsumer#processEvent(DbusEvent, DbusEventDecoder)}
	 * @see com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer#onBootstrapEvent(com.linkedin.databus.core.DbusEvent, com.linkedin.databus.client.pub.DbusEventDecoder)
	 */
	public ConsumerCallbackResult onBootstrapEvent(DbusEvent event,DbusEventDecoder eventDecoder) {
		return processEvent(event, eventDecoder);
	}

	/**
	 * Helper method that prints out the attributes of the Person change event. 
	 * @param event the Databus change event
	 * @param eventDecoder the Event decoder
	 * @return {@link ConsumerCallbackResult#SUCCESS} if successful and {@link ConsumerCallbackResult#ERROR} in case of exceptions/errors
	 */
	private ConsumerCallbackResult processEvent(DbusEvent event, DbusEventDecoder eventDecoder) {
		GenericRecord decodedEvent = eventDecoder.getGenericRecord(event, null);
		if (eventCount % FREQUENCY_OF_LOGGING == 0) {
			try {
				Long key = (Long) decodedEvent.get("key");			
				Utf8 firstName = (Utf8) decodedEvent.get("firstName");
				Utf8 lastName = (Utf8) decodedEvent.get("lastName");
				Long birthDate = (Long) decodedEvent.get("birthDate");
				Utf8 deleted = (Utf8) decodedEvent.get("deleted");
	
				LOGGER.info(" key : " + key + " firstName: "
						+ firstName.toString() + ", lastName: "
						+ lastName.toString() + ", birthDate: " + birthDate
						+ ", deleted: " + deleted.toString());
			} catch (Exception e) {
				LOGGER.error("error processing event : " + decodedEvent);
				return ConsumerCallbackResult.ERROR;
			}
		}
		eventCount += 1;
		return ConsumerCallbackResult.SUCCESS;
	}
	
}
