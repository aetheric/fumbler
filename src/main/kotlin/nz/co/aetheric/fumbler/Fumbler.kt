package nz.co.aetheric.fumbler

import java.util.ResourceBundle

/**
 * TODO
 * @param bundle TODO
 */
class Fumbler(val bundle: ResourceBundle) {

	/**
	 * Used to start building a message from a key.
	 * @param key Not actually required till the message is built.
	 */
	fun msg(key: String = "")
			= MsgBuilder(bundle, key)

	/**
	 * Creates an error builder from a key if provided.
	 */
	fun err(key: String = "")
			= ErrBuilder(bundle, msg(key))

}

