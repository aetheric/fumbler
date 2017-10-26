package nz.co.aetheric.fumbler

import java.util.*

/**
 * TODO
 * @param key     TODO
 * @param context TODO
 */
data class MsgBuilder internal constructor(
		val key: String,
		val context: Map<String, String>

) {

	companion object {

		/**
		 * The regular expression used to search for injection handles in messages.
		 */
		private val propertyRegex = """\$\{(\w+?)\}"""

		/**
		 * The recursive internal function used to resolve keys to messages.
		 * @param key The key of the message to resolve.
		 * @param context The contextual data that needs to be injected.
		 */
		private operator fun get(
			key: String,
			context: MutableMap<String, String> = mutableMapOf()

		): String {
		
			val message = bundle.getString(key)

			return Regex.fromLiteral(propertyRegex)
					.findAll(message)
					.map { it.value to it.groupValues.first() }
					.map { it.first to ( context[it.second] ?: get(it.second).apply { context.put(it.second, this) } ) }
					.fold(message) { msg, ( key, value ) -> msg.replace(key, value) }

		}

	}

	fun key(key: String): Message
			= this.copy(key = key)

	fun put(key: String, value: String): Message
			= this.copy(context = context + ( key to value ))

	fun put(vararg context: Pair<String, String>): Message
			= this.put(context.toMap())

	fun put(context: Map<String, String>): Message
			= this.copy(context = this.context + context)

	fun build(): String = get(key, context.toMutableMap())

}

