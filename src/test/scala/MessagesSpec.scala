import jp.rough_diamond.commons.di.DIContainerTestingHelper._
import jp.rough_diamond.commons.resource.{Messages, Message}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, FunSpec}

/**
 * Created with IntelliJ IDEA.
 * User: e-yamane
 * Date: 12/06/05
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */

class MessagesSpec extends FunSpec with ShouldMatchers with BeforeAndAfter {
    init()

    before {
        attach(ResourceNameHooker)
    }

    after {
        detach(ResourceNameHooker)
    }

    describe("Messagesの仕様") {
        it("生成時にはhasErrorはfalseを返却する事") {
            val m = new Messages
            m.hasError should be (false)
        }
        it("１つでもメッセージが追加されたらhasErrorはtrueを返却する事") {
            val m = new Messages
            m.add("", new Message("foo", "World"))
            m.hasError should be (true)
        }
        it("プロパティは追加した順に取得できる事") {
            val m = new Messages
            m.add("a", new Message("foo", "World"))
            m.add("b", new Message("foo", "Hell"))
            m.add("a", new Message("foo", "Heaven"))
            m.add("c", new Message("foo", "Space"))
            val ite = m.getProperties.iterator()
            ite.next() should be ("a")
            ite.next() should be ("b")
            ite.next() should be ("c")
        }
        it("文字列表現は登録プロパティ順でかつ同一プロパティ内での登録順に生成される事") {
            val m = new Messages
            m.add("a", new Message("foo", "World"))
            m.add("b", new Message("foo", "Hell"))
            m.add("a", new Message("foo", "Heaven"))
            m.add("c", new Message("foo", "Space"))
            m.toString should be (
"""a:Hello World.
a:Hello Heaven.
b:Hello Hell.
c:Hello Space.
""")
        }
    }
}
