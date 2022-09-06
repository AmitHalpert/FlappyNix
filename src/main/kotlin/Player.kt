import java.awt.Graphics
import java.awt.Image
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.geom.Rectangle2D
import javax.swing.ImageIcon
import kotlin.concurrent.thread

class Player(var panel: GameScreen) : Thread(), KeyListener{

    private var last_time = System.nanoTime()

    // constants
    private val playerWidth = 130
    private val playerHeight = 115
    private val gravityAmount = 1
    // player characteristics
    private var playerX : Double = 800.0
    private var playerY : Double = 0.0
    private var velX : Double = 0.0
    private var velY : Double = 0.0
    private var playerHitBox : Rectangle2D? = null
    // animations
    private var ballImage: Image



    init {
        val img = ImageIcon("src/main/resources/nix.png")
        ballImage = img.image



        start()
    }



    override fun run() {
        while (true) {
            val time = System.nanoTime()
            val delta_time = ((time - last_time) / 1000000)
            last_time = time

            updatePlayer()



            velY  += 1.0


            try {
                sleep(20)
            } catch (e: InterruptedException) {
            }

            panel.repaint()

        }
    }

    private fun updatePlayer() {

        //Updates X AND Y Position of the player
        playerX += velX
        playerY += velY

    }

    fun draw(g: Graphics) {
        g.drawImage(ballImage, playerX.toInt(), playerY.toInt(), playerWidth, playerHeight, null)
    }

    override fun keyTyped(key: KeyEvent?) {

    }

    override fun keyPressed(key: KeyEvent?) {
        if (key != null) {
            when(key.keyCode){
                KeyEvent.VK_SPACE -> velY -= 35.0
            }
        }
    }

    override fun keyReleased(key: KeyEvent?) {

    }
}