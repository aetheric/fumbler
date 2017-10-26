package nz.co.aetheric.fumbler

data class ErrBuilder(
		val message: Message,
		val cause: Throwable? = null

) {

	val key get() = message.key

	val context get() = message.context

	fun key(key: String)
			= this.copy(message = message.key("${message.key}.$key"))

	fun cause(cause: Throwable)
			= this.copy(cause = cause)

	fun put(key: String, value: String)
			= this.copy(message = message.put(key, value))

	fun put(vararg context: Pair<String, String>)
			= this.put(context.toMap())

	fun put(context: Map<String, String>)
			= this.copy(message = message.put(context))

	fun build(): Nothing = throw RuntimeException(message, cause)

}
