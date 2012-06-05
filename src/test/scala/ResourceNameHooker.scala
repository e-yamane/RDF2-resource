import jp.rough_diamond.commons.di.DIContainerTestingHelper.DIHook
import jp.rough_diamond.commons.resource.{ResourceManager, LocaleControllerByThreadLocal, LocaleController}

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/06/05
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */

object ResourceNameHooker extends DIHook {
    val lc = new LocaleControllerByThreadLocal

    def getObject[T](`type`: Class[T], key: Any): T = {
        if (key == LocaleController.LOCALE_CONTROLLER_KEY) {
            return lc.asInstanceOf[T]
        } else if (key == ResourceManager.RESOURCE_NAME_KEY) {
            return "a, b".asInstanceOf[T]
        } else {
            return null.asInstanceOf[T]
        }
    }
}
