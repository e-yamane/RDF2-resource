import java.util.Locale
import jp.rough_diamond.commons.resource.LocaleControllerByThreadLocal
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/05/31
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */

class LocaleControllerByThreadLocalSpec extends FunSpec with ShouldMatchers {
    val lc = new LocaleControllerByThreadLocal

    //FIXME このテストはドイツ言語圏では正しく動作しない
    describe("LocaleControllerByThreadLocalの仕様") {
        it("ローケルを変更しても別のスレッドには影響のない事") {
            var dl = Locale.getDefault
            lc.setLocale(Locale.GERMAN);
            var r = new RunnableImpl
            val t = new Thread(r)
            t.start()
            t.join()
            lc.getLocale should be (Locale.GERMAN)
            assert(r.l != Locale.GERMAN)
        }
    }

    class RunnableImpl extends Runnable {
        var l : Locale = null
        def run() : Unit = {
            l = lc.getLocale
        }
    }
}
