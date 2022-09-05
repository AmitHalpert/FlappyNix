import java.awt.Graphics
import java.awt.Image
import javax.swing.ImageIcon

class Player(var panel: GameScreen) : Thread() {
    var x = 0
    var y = 0
    var size = 50
    var ballImage: Image

    init {
        val img = ImageIcon("src/main/resources/nix.png")
        ballImage = img.image
        start()
    }

    override fun run() {
        while (true) {
            try {
                sleep(10)
            } catch (e: InterruptedException) {
            }
            panel.repaint()
        }
    }

    fun draw(g: Graphics) {
        g.drawImage(ballImage, x, y, size, size, null)
    }
}