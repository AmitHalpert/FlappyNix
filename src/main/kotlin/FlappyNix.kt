import java.awt.Color
import java.awt.Graphics
import kotlin.math.pow
import kotlin.math.sqrt

class FlappyNix(var x: Int, var y: Int, var width: Int, var color: Color, var panel: GameScreen) : Thread() {
    fun draw(g: Graphics) {
        g.color = color
        g.fillOval(x - width / 2, y - width / 2, width, width)
    }

    override fun run() {
        var dirx = 6
        while (true) {
                x += dirx


                try {
                    sleep(7)
                } catch (e: InterruptedException) {
                }

        }
    }
}
