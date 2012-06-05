import jp.rough_diamond.commons.di.DIContainerTestingHelper._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, FunSpec}

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/06/05
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */

class MessageSpec extends FunSpec with ShouldMatchers with  BeforeAndAfter {
    init()

    before {
        attach(ResourceNameHooker)
    }

    after {
        detach(ResourceNameHooker)
    }

    describe("Messageの仕様") {
        it("正しく文字列変換がおこなわれる事") {
            val msg = new Message("foo", "World");
            msg.toString should be ("Hello World.")
        }
        it("置換数＞引数の場合例外が発生する事") {
            val msg = new Message("foo");
            try {
                msg.toString should be ("Hello .")
                fail("例外が発生していません。")
            } catch {
                case x =>
            }
        }
        it("置換数＜引数の場合は後続引数は無視して正しく表示される事") {
            val msg = new Message("foo", "World", "Heaven");
            msg.toString should be ("Hello World.")
        }
    }
}
