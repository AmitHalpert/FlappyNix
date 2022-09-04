import java.awt.EventQueue
import javax.swing.JFrame


class FlappyNix : JFrame() {
    init {
        this.initNixerGame()
    }

    private fun initNixerGame() {
        this.add(GameScreen())
        this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.title = "NixerBird"
        this.isResizable = false
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater {
                FlappyNix()
            }
        }
    }
}