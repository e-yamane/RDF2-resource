import java.util.Locale
import jp.rough_diamond.commons.resource.SimpleLocaleController
import org.scalatest.FunSpec

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/05/31
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */

class SimpleLocaleControllerSpec extends FunSpec {
    val lc = new SimpleLocaleController
    describe("SimpleLocaleControllerの仕様") {
        it("nullを指定したら起動時のデフォルトローケルがセットされる事") {
            classOf[SimpleLocaleController].synchronized {
                val dl = Locale.getDefault
                try {
                    Locale.setDefault(Locale.FRANCE)
                    lc.setLocale(null)
                    assert(dl == Locale.getDefault);
                }finally {
                    lc.setLocale(dl)
                }
            }
        }
        it("null以外を指定したらデフォルトローケルが変更される事") {
            classOf[SimpleLocaleController].synchronized {
                val dl = Locale.getDefault
                try {
                    Locale.setDefault(Locale.FRANCE)
                    lc.setLocale(Locale.FRANCE)
                    assert(Locale.FRANCE == Locale.getDefault);
                }finally {
                    lc.setLocale(dl)
                }
            }
        }
    }
}
