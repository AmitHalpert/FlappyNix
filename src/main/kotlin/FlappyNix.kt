import java.awt.Color
import java.awt.Graphics

class FlappyNix(var x: Int, var y: Int, var width: Int, var color: Color, var panel: GameScreen) : Thread() {
    fun draw(g: Graphics) {
        g.color = color
        g.fillOval(x - width / 2, y - width / 2, width, width)
    }

    override fun run() {
        var dirx = 2
        while (true) {
                x += dirx


                try {
                    sleep(20)
                } catch (e: InterruptedException) {
                }

        }
    }
}
