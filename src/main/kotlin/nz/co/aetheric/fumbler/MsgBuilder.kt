package nz.co.aetheric.fumbler

import java.util.ResourceBundle

/**
 * TODO
 * @param key     TODO
 * @param context TODO
 */
data class MsgBuilder internal constructor(
		private val bundle: ResourceBundle,
		val key: String,
		val context: Map<String, String> = emptyMap()

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
			bundle: ResourceBundle,
			key: String,
			context: MutableMap<String, String> = mutableMapOf()

		): String {
		
			val message = bundle.getString(key)

			return Regex.fromLiteral(propertyRegex)
					.findAll(message)
					.map { it.value to it.groupValues.first() }
					.map { it.first to ( context[it.second] ?: get(bundle, it.second).apply { context.put(it.second, this) } ) }
					.fold(message) { msg, ( key, value ) -> msg.replace(key, value) }

		}

	}

	fun key(key: String): MsgBuilder
			= this.copy(key = key)

	fun put(key: String, value: String): MsgBuilder
			= this.copy(context = context + ( key to value ))

	fun put(vararg context: Pair<String, String>): MsgBuilder
			= this.put(context.toMap())

	fun put(context: Map<String, String>): MsgBuilder
			= this.copy(context = this.context + context)

	fun build(): String = get(bundle, key, context.toMutableMap())

}

