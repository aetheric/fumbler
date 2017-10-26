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
Fumbler.source(MessageSource.load("messages"))
Fumbler.err("some.error.key")
	.put("variable", "value")
	.cause(exception)
	.build() # Throws the exception.
```

Note: Each result from the builder is immutable, so messages can be prepared.

```java
FumblerBuilder builder = Fumbler.err("some.error")

if (condition) {
	builder.key("key1").build() # key: some.error.key1

} else {
	builder.key("key2").build() # key: some.error.key2
}
```

