# Fumbler

A lightweight error/exception handling "framework" written in kotlin

## Usage

Include in your project:

```xml
<dependency>
	<groupId>nz.co.aetheric</groupId>
	<artifactId>fumbler</artifactId>
	<version>${version.fumbler}</version>
</dependency>
```

then provide it with the message bundle location and start building errors:

```java
Fumbler fumbler = new Fumbler(MessageSource.load("messages"))
fumbler.err("some.error.key")
	.put("variable", "value")
	.cause(exception)
	.build() # Throws the exception.
```

Note: Each result from the builder is immutable, so messages can be prepared.

```java
ErrBuilder builder = Fumbler.err("some.error")
		.put("some", "context")

if (condition) {
	builder.key("key1").build() # key: some.error.key1

} else {
	builder.key("key2").build() # key: some.error.key2
}
```

If you want to build a message without an error, you totally can.

```java
MsgBuilder builder = Fumbler.msg("some.message")
		.put("some", "context")

validationMsgs.add(builder.key("key1").build())
validationMsgs.add(builder.key("key2").build())
```
