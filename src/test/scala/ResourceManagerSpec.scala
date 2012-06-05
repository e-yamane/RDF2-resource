import java.util.{MissingResourceException, Locale}
import jp.rough_diamond.commons.di.DIContainerTestingHelper
import jp.rough_diamond.commons.di.DIContainerTestingHelper.{init, attach, detach}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, FunSpec}

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/06/01
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */

class ResourceManagerSpec extends FunSpec with ShouldMatchers with  BeforeAndAfter {
    init()

    before {
        attach(ResourceNameHooker)
    }

    after {
        detach(ResourceNameHooker)
    }

    describe("ResourceManagerの仕様") {
        it("LocaleControllerに基づくロケールのプロパティファイルを取得している事") {
            try {
                LocaleController.getController.setLocale(Locale.FRANCE)
                val rb = ResourceManager.getResource
                rb.getString("a01") should be ("france")
            }  finally {
                LocaleController.getController.setLocale(null)
            }
        }
        it("最初のプロパティファイルにだけ存在するプロパティが取得できる事") {
            val rb = ResourceManager.getResource
            rb.getString("a01") should be ("aaa")
        }

        it("最後のプロパティファイルにだけ存在するプロパティが取得できる事") {
            val rb = ResourceManager.getResource
            rb.getString("a03") should be ("xzz")
        }
        it("複数のプロパティファイルに存在するプロパティの場合最初に現れた値を取得できる事") {
            val rb = ResourceManager.getResource
            rb.getString("a02") should be ("abc")
        }
        it("全てのプロパティファイルに存在しない場合はMissingResourceExceptionがスローされる事") {
            val rb = ResourceManager.getResource
            try {
                rb.getString("not specificate")
                fail("例外が発生していません")
            } catch {
                case e : MissingResourceException =>
                case x => fail("その他の例外です")
            }
        }
    }
}
