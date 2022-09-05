import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JPanel

class GameScreen : JPanel() {


    var player: Player
    var vec: Array<FlappyNix?>
    var background: Image? = null
    var moveFlag = false

    init {
        background = ImageIcon("src/main/resources/background.png").image
        player = Player(this)
        vec = arrayOfNulls<FlappyNix>(10)
        for (i in vec.indices) {
            val c = Color((Math.random() * 256).toInt(), (Math.random() * 256).toInt(), (Math.random() * 256).toInt())
            val w = (Math.random() * 50).toInt() + 10
            val x = (i + 1) * 90

            // 1-9=2022
            // keep y from 20 to 470 in screen borders
            var y = (Math.random() * (500 - w)).toInt()
            if (y < w) y += w
            vec[i] = FlappyNix(x, y, w, c, this)
        }
        try {
            Thread.sleep(200)
        } catch (e: InterruptedException) {
        }
        isFocusable = true
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(background, 0, 0, width, height, null)

        for (i in vec.indices) if (vec[i]?.isAlive() == true) vec[i]?.draw(g)
        player.draw(g)
    }



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Get screen size
            val screenSize = Toolkit.getDefaultToolkit().screenSize
            val ScreenWidth = screenSize.getWidth()
            val ScreenHeight = screenSize.getHeight()

            // setup Jframe
            val frame = JFrame("FlappyNix")
            val bp = GameScreen()
            frame.add(bp)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.setSize(ScreenWidth.toInt(), ScreenHeight.toInt())

            frame.isResizable = false
            frame.isVisible = true
            frame.isFocusable = false


            for (i in bp.vec.indices) {
                bp.vec[i]?.start()
            }
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
            }
            bp.moveFlag = true
        }
    }
}