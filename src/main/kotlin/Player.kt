import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JPanel

class Player{

    var PlayerY = 0;
    val JumpForce = 300
    var PlayerImage: Image? = null


    init {
        loadImages()
    }

    private fun loadImages() {

        val iid = ImageIcon("src/main/resources/nix.png")
        PlayerImage = iid.image

    }

}