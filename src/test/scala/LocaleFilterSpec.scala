import java.util.Locale
import javax.servlet.http.HttpServletRequest
import javax.servlet.{ServletResponse, ServletRequest, FilterChain}
import jp.rough_diamond.commons.di.{DIContainerTestingHelper, AbstractDIContainer, DIContainerFactory}
import org.mockito.Mockito
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/05/31
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */

class LocaleFilterSpec extends FunSpec with ShouldMatchers {
    import DIContainerTestingHelper._
    init()

    describe("doFilterが呼び出された際の仕様") {
        it("クライアントのロケールをLocaleControllerにセットする事") {
            classOf[SimpleLocaleController].synchronized  {
                val l = Locale.getDefault
                val listener = new DIHook {
                    def getObject[T](`type`: Class[T], key: Any): T = {
                        if(key == LocaleController.LOCALE_CONTROLLER_KEY) {
                            lc.asInstanceOf[T]
                        } else {
                            null.asInstanceOf[T]
                        }
                    }
                }
                attach(listener)
                try {
                    val req = Mockito.mock(classOf[HttpServletRequest]);
                    val chain = new FilterChainImpl
                    Mockito.when(req.getLocale).thenReturn(Locale.CHINA);
                    val lf = new LocaleFilter();
                    lf.doFilter(req, null, chain);
                    chain.l should be (Locale.CHINA)
                    lc.getLocale should be (l)
                } finally {
                    detach(listener)
                }
            }
        }
    }

    class FilterChainImpl extends FilterChain {
        var l:Locale  = null
        @Override
        def doFilter(request: ServletRequest, response: ServletResponse) {
            l = lc.getLocale
        }
    }

    lazy val lc = new LocaleControllerByThreadLocal
}
