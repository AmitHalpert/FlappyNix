import java.awt.Graphics
import java.awt.Image
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.geom.Rectangle2D
import javax.swing.ImageIcon

class Player(var panel: GameScreen) : Thread(), KeyListener{

    // constants
    private val playerWidth = 130
    private val playerHeight = 115
    private val gravityAmount = 300
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
        playerHitBox?.setRect(32.0,34.0,54.0,54.0)


        start()
    }



    override fun run() {
        while (true) {


            updatePlayer()


            try {
                sleep(10)
            } catch (e: InterruptedException) {
            }
            panel.repaint()
        }
    }

    fun updatePlayer() {

        //Updates X AND Y Position of the player
        playerX += velX
        playerY += velY

    }

    fun draw(g: Graphics) {
        g.drawImage(ballImage, playerX.toInt(), playerY.toInt(), playerWidth, playerHeight, null)
    }

    override fun keyTyped(p0: KeyEvent?) {

    }

    override fun keyPressed(p0: KeyEvent?) {
        println("deezsssssssssssssssssss")
    }

    override fun keyReleased(p0: KeyEvent?) {

    }
}