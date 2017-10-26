package nz.co.aetheric.fumbler

import java.util.*

/**
 * TODO
 * @param bundle TODO
 */
class Fumbler(val bundle: ResourceBundle) {

	/**
	 * Used to start building a message from a key.
	 * @param key Not actually required till the message is built.
	 */
	@JvmStatic
	fun msg(key: String = "")
			= MsgBuilder(key)

	/**
	 * Creates an error builder from a key if provided.
	 */
	@JvmStatic
	fun err(key: String = "")
			= ErrBuilder(msg(key))

}

