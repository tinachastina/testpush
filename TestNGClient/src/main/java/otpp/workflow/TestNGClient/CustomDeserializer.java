package otpp.workflow.TestNGClient;

/*public class CustomDeserializer extends JsonDeserializer<RequestBody>{
@Override
public RequestBody deserialize(JsonParser jp, DeserializationContext ctxt) 
  throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    int id = (Integer) ((IntNode) node.get("allVariables")).numberValue();
    String itemName = node.get("itemName").asText();
    int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();

    return new Item(id, itemName, new User(userId, null));
}

}*/
/*
* Copyright 2002-2014 the original author or authors.
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
* Implementation of {@link org.springframework.http.converter.HttpMessageConverter HttpMessageConverter} that
* can read and write JSON using <a href="http://jackson.codehaus.org/">Jackson 2.x's</a> {@link ObjectMapper}.
*
* <p>This converter can be used to bind to typed beans, or untyped {@link java.util.HashMap HashMap} instances.
*
* <p>By default, this converter supports {@code application/json}. This can be overridden by setting the
* {@link #setSupportedMediaTypes supportedMediaTypes} property.
*
* <p>Tested against Jackson 2.2 and 2.3; compatible with Jackson 2.0 and higher.
*
* @author Arjen Poutsma
* @author Keith Donald
* @author Rossen Stoyanchev
* @author Juergen Hoeller
* @since 3.1.2
*/
public class CustomDeserializer extends AbstractHttpMessageConverter<Object>
	implements GenericHttpMessageConverter<Object> {

public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

// Check for Jackson 2.3's overloaded canDeserialize/canSerialize variants with cause reference
private static final boolean jackson23Available =
		ClassUtils.hasMethod(ObjectMapper.class, "canDeserialize", JavaType.class, AtomicReference.class);


private ObjectMapper objectMapper = new ObjectMapper();

private String jsonPrefix;

private Boolean prettyPrint;


/**
 * Construct a new {@code MappingJackson2HttpMessageConverter}.
 */
public CustomDeserializer() {
	super(new MediaType("application", "json", DEFAULT_CHARSET),
			new MediaType("application", "*+json", DEFAULT_CHARSET));
}


/**
 * Set the {@code ObjectMapper} for this view.
 * If not set, a default {@link ObjectMapper#ObjectMapper() ObjectMapper} is used.
 * <p>Setting a custom-configured {@code ObjectMapper} is one way to take further
 * control of the JSON serialization process. For example, an extended
 * {@link com.fasterxml.jackson.databind.ser.SerializerFactory}
 * can be configured that provides custom serializers for specific types.
 * The other option for refining the serialization process is to use Jackson's
 * provided annotations on the types to be serialized, in which case a
 * custom-configured ObjectMapper is unnecessary.
 */
public void setObjectMapper(ObjectMapper objectMapper) {
	Assert.notNull(objectMapper, "ObjectMapper must not be null");
	this.objectMapper = objectMapper;
	configurePrettyPrint();
}

/**
 * Return the underlying {@code ObjectMapper} for this view.
 */
public ObjectMapper getObjectMapper() {
	return this.objectMapper;
}

/**
 * Specify a custom prefix to use for this view's JSON output.
 * Default is none.
 * @see #setPrefixJson
 */
public void setJsonPrefix(String jsonPrefix) {
	this.jsonPrefix = jsonPrefix;
}

/**
 * Indicate whether the JSON output by this view should be prefixed with "{} &&". Default is false.
 * <p>Prefixing the JSON string in this manner is used to help prevent JSON Hijacking.
 * The prefix renders the string syntactically invalid as a script so that it cannot be hijacked.
 * This prefix does not affect the evaluation of JSON, but if JSON validation is performed on the
 * string, the prefix would need to be ignored.
 * @see #setJsonPrefix
 */
public void setPrefixJson(boolean prefixJson) {
	this.jsonPrefix = (prefixJson ? "{} && " : null);
}

/**
 * Whether to use the {@link DefaultPrettyPrinter} when writing JSON.
 * This is a shortcut for setting up an {@code ObjectMapper} as follows:
 * <pre class="code">
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
 * converter.setObjectMapper(mapper);
 * </pre>
 */
public void setPrettyPrint(boolean prettyPrint) {
	this.prettyPrint = prettyPrint;
	configurePrettyPrint();
}

private void configurePrettyPrint() {
	if (this.prettyPrint != null) {
		this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint);
	}
}


@Override
public boolean canRead(Class<?> clazz, MediaType mediaType) {
	return canRead(clazz, null, mediaType);
}

public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
	JavaType javaType = getJavaType(type, contextClass);
	if (!jackson23Available || !logger.isWarnEnabled()) {
		return (this.objectMapper.canDeserialize(javaType) && canRead(mediaType));
	}
	AtomicReference<Throwable> causeRef = new AtomicReference<Throwable>();
	if (this.objectMapper.canDeserialize(javaType, causeRef) && canRead(mediaType)) {
		return true;
	}
	Throwable cause = causeRef.get();
	if (cause != null) {
		String msg = "Failed to evaluate deserialization for type " + javaType;
		if (logger.isDebugEnabled()) {
			logger.warn(msg, cause);
		}
		else {
			logger.warn(msg + ": " + cause);
		}
	}
	return false;
}

@Override
public boolean canWrite(Class<?> clazz, MediaType mediaType) {
	if (!jackson23Available || !logger.isWarnEnabled()) {
		return (this.objectMapper.canSerialize(clazz) && canWrite(mediaType));
	}
	AtomicReference<Throwable> causeRef = new AtomicReference<Throwable>();
	if (this.objectMapper.canSerialize(clazz, causeRef) && canWrite(mediaType)) {
		return true;
	}
	Throwable cause = causeRef.get();
	if (cause != null) {
		String msg = "Failed to evaluate serialization for type [" + clazz + "]";
		if (logger.isDebugEnabled()) {
			logger.warn(msg, cause);
		}
		else {
			logger.warn(msg + ": " + cause);
		}
	}
	return false;
}

@Override
protected boolean supports(Class<?> clazz) {
	// should not be called, since we override canRead/Write instead
	throw new UnsupportedOperationException();
}

@Override
protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
		throws IOException, HttpMessageNotReadableException {

	JavaType javaType = getJavaType(clazz, null);
	return readJavaType(javaType, inputMessage);
}

public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
		throws IOException, HttpMessageNotReadableException {

	JavaType javaType = getJavaType(type, contextClass);
	return readJavaType(javaType, inputMessage);
}

private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
	try {
		
		String parseInput = "["+convertStreamToString(inputMessage.getBody())+"]";
		InputStream stream = new ByteArrayInputStream(parseInput.getBytes(StandardCharsets.UTF_8));
		return this.objectMapper.readValue(stream, javaType);
	}
	catch (IOException ex) {
		throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
	}
}
static String convertStreamToString(java.io.InputStream is){
	java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	return s.hasNext() ? s.next() : "";
}

@Override
protected void writeInternal(Object object, HttpOutputMessage outputMessage)
		throws IOException, HttpMessageNotWritableException {

	JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
	// The following has been deprecated as late as Jackson 2.2 (April 2013);
	// preserved for the time being, for Jackson 2.0/2.1 compatibility.
	@SuppressWarnings("deprecation")
	JsonGenerator jsonGenerator =
			this.objectMapper.getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);

	// A workaround for JsonGenerators not applying serialization features
	// https://github.com/FasterXML/jackson-databind/issues/12
	if (this.objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
		jsonGenerator.useDefaultPrettyPrinter();
	}

	try {
		if (this.jsonPrefix != null) {
			jsonGenerator.writeRaw(this.jsonPrefix);
		}
		this.objectMapper.writeValue(jsonGenerator, object);
	}
	catch (JsonProcessingException ex) {
		throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
	}
}

/**
 * Return the Jackson {@link JavaType} for the specified type and context class.
 * <p>The default implementation returns {@code typeFactory.constructType(type, contextClass)},
 * but this can be overridden in subclasses, to allow for custom generic collection handling.
 * For instance:
 * <pre class="code">
 * protected JavaType getJavaType(Type type) {
 *   if (type instanceof Class && List.class.isAssignableFrom((Class)type)) {
 *     return TypeFactory.collectionType(ArrayList.class, MyBean.class);
 *   } else {
 *     return super.getJavaType(type);
 *   }
 * }
 * </pre>
 * @param type the type to return the java type for
 * @param contextClass a context class for the target type, for example a class
 * in which the target type appears in a method signature, can be {@code null}
 * signature, can be {@code null}
 * @return the java type
 */
protected JavaType getJavaType(Type type, Class<?> contextClass) {
	return this.objectMapper.getTypeFactory().constructType(type, contextClass);
}

/**
 * Determine the JSON encoding to use for the given content type.
 * @param contentType the media type as requested by the caller
 * @return the JSON encoding to use (never {@code null})
 */
protected JsonEncoding getJsonEncoding(MediaType contentType) {
	if (contentType != null && contentType.getCharSet() != null) {
		Charset charset = contentType.getCharSet();
		for (JsonEncoding encoding : JsonEncoding.values()) {
			if (charset.name().equals(encoding.getJavaName())) {
				return encoding;
			}
		}
	}
	return JsonEncoding.UTF8;
}

}

