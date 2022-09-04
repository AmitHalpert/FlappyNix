import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.concurrent.thread


class GameScreen : JPanel(), ActionListener {


    private var screenSize = Toolkit.getDefaultToolkit().screenSize
    private val ScreenWidth = screenSize.getWidth()
    private val ScreenHeight = screenSize.getHeight()

    private var timer: Timer? = null

    private var player = Player()



    init {
        background = Color.red
        isFocusable = true
        preferredSize = Dimension(ScreenWidth.toInt(), ScreenHeight.toInt())
        timer = Timer(60, this)
        timer!!.start()
    }



    override fun actionPerformed(e: ActionEvent) {

        move()


        repaint()
    }

    private fun move(){
        player.PlayerY += 10
    }


    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        g.drawImage(player.PlayerImage, ((ScreenWidth.toInt() - 200) / 2), player.PlayerY, 200, 200, this)
        Toolkit.getDefaultToolkit().sync()
    }


}