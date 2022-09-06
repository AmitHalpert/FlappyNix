import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JPanel

class GameScreen : JPanel() {


    var player: Player
    var background: Image? = null
    var moveFlag = false

    init {
        background = ImageIcon("src/main/resources/background.png").image
        player = Player(this)

        addKeyListener(player)

        isFocusable = true
    }


    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(background, 0, 0, width, height, null)

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
            frame.revalidate();
            frame.repaint();


            try {
                Thread.sleep(50)
            } catch (e: InterruptedException) {
            }
            bp.moveFlag = true
        }
    }
}